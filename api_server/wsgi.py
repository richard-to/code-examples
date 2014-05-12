from codebot import factory

app = factory.create_app(static_folder='/vagrant/site')
if __name__ == '__main__':
    app.run(host=app.config['HOST'], port=app.config['PORT'])