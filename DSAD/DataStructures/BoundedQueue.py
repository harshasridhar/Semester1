class Queue:
	head = -1
	tail = -1
	array = []
	size = 0

	def __init__(self, size):
		self.head = 0
		self.tail = -1
		self.size = size
		self.array = [0] * size
	
	def isFull(self):
		return self.size

	def enqueue(self, element):
		if self.tail == self.size -1:
			print('Queue overflow!')
			return
		self.tail += 1
		self.array[self.tail] = element
	
	def dequeue(self):
		if self.head == self.tail + 1:
			print('Queue underflow!')
			return
		element = self.array[self.head]
		self.head += 1
		return element

	def print(self):
		if self.head == self.tail +1:
			print('Queue is empty')
		else:
			print('Elements:',end =" ")
			print([ self.array[i] for i in range(self.head,self.tail+1)]) 
