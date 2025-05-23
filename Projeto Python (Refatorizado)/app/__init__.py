from flask import Flask
from flask_wtf import CSRFProtect
from flask_migrate import Migrate
from flask_login import LoginManager
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime


db = SQLAlchemy()
csrf = CSRFProtect()
migrate = Migrate()
login = LoginManager()
login.login_view = 'login'

def br_datetime(value, format='%d/%m/%Y'):
    if isinstance(value, datetime):
        return value.strftime(format)
    return value

def create_app():
    app = Flask(__name__)
    app.config.from_object('config.Config')
    
    db.init_app(app)
    csrf.init_app(app)
    migrate.init_app(app)
    login.init_app(app)

    app.jinja_env.filters['br_datetime'] = br_datetime

    from . import models
    from .routes import bp as main_bp
    app.register_blueprint(main_bp)

    with app.app_context():
        db.create_all()
        
        @login.user_loader
        def load_user(user_id):
            return models.User.query.get(int(user_id))
        
        return app