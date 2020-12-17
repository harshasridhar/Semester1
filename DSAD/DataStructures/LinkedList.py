class Node:
	value = None
	next = None
	
	def __init__(self, value):
		self.value = value
		self.next = None

class LinkedList:
	head = None

	def __init__(self):
		self.head = None
	
	def addNode(self, value):
		if self.head is None:
			self.head = Node(value)
		else:
			current = self.head
			while current.next is not None:
				current = current.next
			current.next = Node(value)
	
	def deleteNodeByValue(self, value):
		if self.head.value == value:
			self.head = self.head.next
		else:
			current = self.head
			previous = self.head
			while current is not None and current.value != value :
				previous = current
				current = current.next
			if current is not None:
				previous.next = current.next
			else:
				print('No node found')
	
	def deleteNodeByPosition(self, position):
		position -= 1 #considering given position starts from 1
		if position == 0:
			self.head = self.head.next
		else:
			i = 0
			current = self.head
			previous = self.head
			while i < position and i != position:
				previous = current
				current = current.next
				i +=1
			if i > position:
				print('Invalid position')
			else:
				previous.next = current.next
	
	def print(self):
		current = self.head
		while current is not None:
			print(current.value, end =" ")
			current = current.next
		print()
