class Node:
	def __init__(self, value):
		self.left = None
		self.right = None
		self.value = value
	
	def addNode(self, value):
		if self.value :
			if value < self.value:
				if self.left is None:
					self.left = Node(value)
				else:
					self.left.addNode(value)
			elif value > self.value:
				if self.right is None:
					self.right = Node(value)
				else:
					self.right.addNode(value)
			else:
				self.value = value
	def print(self,order = "inorder"):
		if order == "inorder":
			if self.left is not None:
				self.left.print(order)
			print(self.value, end = " ")
			if self.right is not None:
				self.right.print(order)
		elif order == "preorder":
			print(self.value, end=" ")
			if self.left is not None:
				self.left.print(order)
			if self.right is not None:
				self.right.print(order)
		elif order == "postorder":
			if self.left is not None:
				self.left.print(order)
			if self.right is not None:
				self.right.print(order)
			print(self.value, end =" ")
