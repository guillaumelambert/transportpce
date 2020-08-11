#!/usr/bin/python3

from flask import Flask, request

app = Flask(__name__)

@app.route('/')
def main():
    return """<html>
        <body>
        test
        </body>
        </html>"""



if __name__ == '__main__':
    app.run(port=5000)
