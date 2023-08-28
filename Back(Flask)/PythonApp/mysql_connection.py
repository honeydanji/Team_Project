import mysql.connector

def get_connection() :
    connection = mysql.connector.connect(
        host='localhost',
        database='k3',
        user='root',
        password='1234')
    return connection