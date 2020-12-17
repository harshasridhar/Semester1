class Node:
	value = None 
	previous = None
	next = None
	
	def __init__(self, value):
		self.value = value
		self.previous = None
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
			new_node = Node(value)
			current.next = new_node
			new_node.previous = current
	
	def deleteNodeByValue(self, value):
		if self.head.value == value:
			self.head = self.head.next
			self.head.previous = None
		else:
			previous = self.head
			current = self.head
			while current is not None and current.value != value:
				previous = current
				current = current.next
			if current is None:
				print('Node with given value not found!')
			else:
				previous.next = current.next
				current.previous = None
				current.next = None

	def deleteNodeByPosition(self, position):
		position -= 1
		if position == 0:
			self.head = self.head.next
			self.head.next.previous = None
		else:
			i = 0
			previous = self.head
			current = self.head
			while i < position and i != position:
				previous = current
				current = current.next
				i +=1
			if i > position:
				print('Invalid position')
			else:
				current.previous = None
				previous.next = current.next
				current.next = None
	def print(self):
		current = self.head
		while current is not None:
			print(current.value, end =" ")
			current = current.next
		print()
		
