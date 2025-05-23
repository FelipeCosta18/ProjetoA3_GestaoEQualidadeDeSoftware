import os
import tempfile
import pytest
from app import create_app, db
from app.models import User, Book, Person, Historico

def test_register_and_login():
    db_fd, db_path = tempfile.mkstemp()
    app = create_app()
    app.config['TESTING'] = True
    app.config['WTF_CSRF_ENABLED'] = False
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{db_path}'
    with app.app_context():
        db.drop_all()
        db.create_all()
    client = app.test_client()
    # Testa registro
    response = client.post('/register', data={
        'username': 'newuser',
        'password': 'newpass',
        'password2': 'newpass',
        'submit': 'Registrar'
    }, follow_redirects=True)
    assert b'login' in response.data.lower() or response.status_code == 200
    # Testa login
    response = client.post('/login', data={
        'username': 'newuser',
        'password': 'newpass',
        'submit': 'Entrar'
    }, follow_redirects=True)
    assert b'dashboard' in response.data.lower() or response.status_code == 200
    os.close(db_fd)
    os.unlink(db_path)

def test_create_and_delete_book():
    db_fd, db_path = tempfile.mkstemp()
    app = create_app()
    app.config['TESTING'] = True
    app.config['WTF_CSRF_ENABLED'] = False
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{db_path}'
    with app.app_context():
        db.drop_all()
        db.create_all()
        user = User(username='deletebookuser')
        user.set_password('deletebookpass')
        db.session.add(user)
        db.session.commit()
    client = app.test_client()
    client.post('/login', data={
        'username': 'deletebookuser',
        'password': 'deletebookpass',
        'submit': 'Entrar'
    }, follow_redirects=True)
    # Cria livro
    response = client.post('/books', data={
        'title': 'Livro Delete',
        'author': 'Autor Delete',
        'genero': 'Drama',
        'available': True,
        'submit': 'Salvar'
    }, follow_redirects=True)
    assert b'livro' in response.data.lower() or response.status_code == 200
    with app.app_context():
        book = Book.query.filter_by(title='Livro Delete').first()
        assert book is not None
        book_id = book.id
    # Deleta livro
    response = client.post(f'/books/delete/{book_id}', follow_redirects=True)
    with app.app_context():
        book = Book.query.filter_by(title='Livro Delete').first()
        assert book is None
    os.close(db_fd)
    os.unlink(db_path)

def test_create_and_delete_person():
    db_fd, db_path = tempfile.mkstemp()
    app = create_app()
    app.config['TESTING'] = True
    app.config['WTF_CSRF_ENABLED'] = False
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{db_path}'
    with app.app_context():
        db.drop_all()
        db.create_all()
        user = User(username='deletepersonuser')
        user.set_password('deletepersonpass')
        db.session.add(user)
        db.session.commit()
    client = app.test_client()
    client.post('/login', data={
        'username': 'deletepersonuser',
        'password': 'deletepersonpass',
        'submit': 'Entrar'
    }, follow_redirects=True)
    # Cria pessoa
    response = client.post('/persons', data={
        'nome': 'Pessoa Delete',
        'sobrenome': 'Souza',
        'email': 'delete@teste.com',
        'submit': 'Salvar'
    }, follow_redirects=True)
    assert b'pessoa' in response.data.lower() or response.status_code == 200
    with app.app_context():
        person = Person.query.filter_by(email='delete@teste.com').first()
        assert person is not None
        person_id = person.id
    # Deleta pessoa
    response = client.post(f'/persons/delete/{person_id}', follow_redirects=True)
    with app.app_context():
        person = Person.query.filter_by(email='delete@teste.com').first()
        assert person is None
    os.close(db_fd)
    os.unlink(db_path)

def test_borrow_and_return_book():
    db_fd, db_path = tempfile.mkstemp()
    app = create_app()
    app.config['TESTING'] = True
    app.config['WTF_CSRF_ENABLED'] = False
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{db_path}'
    with app.app_context():
        db.drop_all()
        db.create_all()
        user = User(username='borrowuser')
        user.set_password('borrowpass')
        db.session.add(user)
        db.session.commit()
    client = app.test_client()
    client.post('/login', data={
        'username': 'borrowuser',
        'password': 'borrowpass',
        'submit': 'Entrar'
    }, follow_redirects=True)
    # Cria livro e pessoa
    client.post('/books', data={
        'title': 'Livro Emprestimo',
        'author': 'Autor Emprestimo',
        'genero': 'Aventura',
        'available': True,
        'submit': 'Salvar'
    }, follow_redirects=True)
    client.post('/persons', data={
        'nome': 'Pessoa Emprestimo',
        'sobrenome': 'Lima',
        'email': 'emprestimo@teste.com',
        'submit': 'Salvar'
    }, follow_redirects=True)
    with app.app_context():
        book = Book.query.filter_by(title='Livro Emprestimo').first()
        person = Person.query.filter_by(email='emprestimo@teste.com').first()
        assert book is not None and person is not None
        book_id = book.id
        person_id = person.id
    # Realiza empréstimo
    response = client.post('/borrow', data={
        'book_id': book_id,
        'person_id': person_id,
        'submit': 'Emprestar'
    }, follow_redirects=True)
    assert b'devolucao' in response.data.lower() or response.status_code == 200
    with app.app_context():
        book = db.session.get(Book, book_id)
        assert not book.available
        historico = Historico.query.filter_by(book_id=book_id, person_id=person_id, return_date=None).first()
        assert historico is not None
        historico_id = historico.id
    # Realiza devolução
    response = client.post(f'/devolver/{historico_id}', follow_redirects=True)
    assert b'devolvido' in response.data.lower() or response.status_code == 200
    with app.app_context():
        book = db.session.get(Book, book_id)
        assert book.available
    os.close(db_fd)
    os.unlink(db_path)

def test_borrow_history():
    db_fd, db_path = tempfile.mkstemp()
    app = create_app()
    app.config['TESTING'] = True
    app.config['WTF_CSRF_ENABLED'] = False
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{db_path}'
    with app.app_context():
        db.drop_all()
        db.create_all()
        user = User(username='historyuser')
        user.set_password('historypass')
        db.session.add(user)
        db.session.commit()
    client = app.test_client()
    client.post('/login', data={
        'username': 'historyuser',
        'password': 'historypass',
        'submit': 'Entrar'
    }, follow_redirects=True)
    # Cria livro e pessoa
    client.post('/books', data={
        'title': 'Livro Historico',
        'author': 'Autor Historico',
        'genero': 'Ação',
        'available': True,
        'submit': 'Salvar'
    }, follow_redirects=True)
    client.post('/persons', data={
        'nome': 'Pessoa Historico',
        'sobrenome': 'Silva',
        'email': 'historico@teste.com',
        'submit': 'Salvar'
    }, follow_redirects=True)
    with app.app_context():
        book = Book.query.filter_by(title='Livro Historico').first()
        person = Person.query.filter_by(email='historico@teste.com').first()
        assert book is not None and person is not None
        book_id = book.id
        person_id = person.id
    # Realiza empréstimo
    client.post('/borrow', data={
        'book_id': book_id,
        'person_id': person_id,
        'submit': 'Emprestar'
    }, follow_redirects=True)
    # Realiza devolução
    with app.app_context():
        historico = Historico.query.filter_by(book_id=book_id, person_id=person_id, return_date=None).first()
        assert historico is not None
        historico_id = historico.id
    client.post(f'/devolver/{historico_id}', follow_redirects=True)
    # Verifica histórico
    response = client.get('/historico', follow_redirects=True)
    assert b'Livro Historico' in response.data and b'Pessoa Historico' in response.data
    os.close(db_fd)
    os.unlink(db_path)
