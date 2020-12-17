class Stack:
	top = -1
	array = []

	def __init__(self):
		self.top = -1
		self.array = []

	def is_empty(self):
		if self.top == -1:
			return True
		return False

	def push(self, element):
		self.array.append(element)
		self.top += 1
	
	def pop(self):
		if self.top == -1:
			print('Stack underflow')
			return
		self.top -= 1
		return self.array.pop()
	
	def print(self):
		print('UnBounded Stack: #Elements: '+str(len(self.array)))
		print('Elements:', end = ' ')
		print(self.array)
