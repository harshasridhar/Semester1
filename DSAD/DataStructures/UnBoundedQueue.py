class Queue:
	array = []
	
	def __init__(self):
		self.array = []
	
	def enqueue(self,element):
		self.array.append(element)
	
	def dequeue(self):
		if len(self.array) == 0:
			print('Queue empty')
		else:
			return self.array.pop(0)

	def print(self):
		print('Elements: ',end = " ")
		print(self.array)
