class Stack:
	top = -1
	size = 0
	array = []

	def __init__(self,size):
		self.top = -1
		self.size = size
		self.array = [0]* size
	
	def is_empty(self):
		if self.top == -1:
			return True
		return False
	
	def push(self, element):
		if self.top == self.size -1:
			print('Stack overflow!')
			return
		self.top+=1
		self.array[self.top] = element

	def pop(self):
		if self.top == -1:
			print('Stack underflow!')
			return
		removed_element = self.array[self.top]
		self.top -= 1
		return removed_element
	
	def print(self):
		print('Bounded Stack: ')
		print('Stack elements:',end =" ")
		if self.top == -1:
			print("[]")
		else:
			print([self.array[i] for i in range(0,self.top+1)])
