#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# The Quote Of The Day Module

import socket
import threading
import random

class server(object):
	""" This class implements all the QOTD protocol"""
	def __init__(self, host, port):
		self.host = host
		self.port = port
		self.socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
		self.socket.bind((self.host, self.port))

	def listen(self):
		self.socket.listen(10)
		while True:
			client, address = self.socket.accept()
			threading.Thread(target = self.listenToClient,args = (client,address)).start()

	def listenToClient(self, client, address):
		sendmsg = self.server()
		objectbyte = sendmsg.encode()
		client.send(objectbyte)
		client.close()

	def server(self):
		linearray = []
		reader = open('../qotd.txt', 'r')
		for line in reader.readlines():
			linearray.append(line)
		quote = random.choice(linearray)
		quote = quote.split("|")
		response = quote[0] + "\n" + quote[1]
		return response
try:
	myserv = server("0.0.0.0", 17)
	myserv.listen()
except KeyboardInterrupt:
	print(" pressed. Exiting...")
