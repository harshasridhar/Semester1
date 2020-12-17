class Node:
	value = None
	lChild = None
	rChild = None

	def __init__(self, value):
		self.value = value
		self.lChild = None
		self.rChild = None
	
	def addLChild(self,value):
		self.lChild = Node(value)
	
	def addRChild(self,value):
		self.rChild = Node(value)	

	def print(self, order = "inorder"):
		if order == "inorder":
			if self.lChild is not None:
				self.lChild.print(order)
			print(self.value,end =' ')
			if self.rChild is not None:
				self.rChild.print(order)
		elif order == "preorder":
			print(self.value, end =' ')
			if self.lChild is not None:
				self.lChild.print(order)
			if self.rChild is not None:
				self.rChild.print(order)
		elif order == "postorder":
			if self.lChild is not None:
				self.lChild.print(order)
			if self.rChild is not None:
				self.rChild.print(order)
			print(self.value, end =' ')
