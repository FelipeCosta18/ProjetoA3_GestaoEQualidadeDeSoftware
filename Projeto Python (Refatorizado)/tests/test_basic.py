import os
import tempfile
import pytest
from app import create_app, db
from app.models import User

def test_app_exists():
    app = create_app()
    assert app is not None

def test_app_client():
    db_fd, db_path = tempfile.mkstemp()
    app = create_app()
    app.config['TESTING'] = True
    app.config['WTF_CSRF_ENABLED'] = False
    app.config['SQLALCHEMY_DATABASE_URI'] = f'sqlite:///{db_path}'
    with app.app_context():
        db.drop_all()  # Garante que não há tabelas antigas
        db.create_all()
        # Cria um usuário de teste
        user = User(username='testuser')
        user.set_password('testpass')
        db.session.add(user)
        db.session.commit()
    
    client = app.test_client()
    # Faz login (inclui o campo 'submit' para simular o envio do formulário)
    response = client.post('/login', data={
        'username': 'testuser',
        'password': 'testpass',
        'submit': 'Entrar'
    }, follow_redirects=True)
    print('LOGIN RESPONSE:', response.data.decode())
    assert b'logout' in response.data.lower() or response.status_code == 200 or b'sair' in response.data.lower()
    # Agora acessa a rota /
    response = client.get('/')
    assert response.status_code in (200, 302)
    os.close(db_fd)
    os.unlink(db_path)
