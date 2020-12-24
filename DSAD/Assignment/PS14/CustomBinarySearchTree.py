class Node:
	def __init__(self, word, meaning):
		self.left = None
		self.right = None
		self.word = word
		self.meaning = meaning
	
	def addNode(self, word, meaning):
		if self.word :
			if word < self.word:
				if self.left is None:
					self.left = Node(word, meaning)
				else:
					if self.left.word < word:
						new_node = Node(word, meaning)
						new_node.left = self.left
						self.left = new_node
					else:
						self.left.addNode(word, meaning)
			elif word > self.word:
				if self.right is None:
					self.right = Node(word, meaning)
				else:
					if self.right.word > word:
						new_node = Node(word, meaning)
						new_node.right = self.right
						self.right = new_node
					else:
						self.right.addNode(word, meaning)
			else:
				self.word = word
				self.meaning = meaning
	def print(self,order = "inorder"):
		if order == "inorder":
			if self.left is not None:
				self.left.print(order)
			print(self.word, end = " ")
			if self.right is not None:
				self.right.print(order)
		elif order == "preorder":
			print(self.word, end=" ")
			if self.left is not None:
				self.left.print(order)
			if self.right is not None:
				self.right.print(order)
		elif order == "postorder":
			if self.left is not None:
				self.left.print(order)
			if self.right is not None:
				self.right.print(order)
			print(self.word, end =" ")
	
	def __contains__(self, word):
		if word > self.word and self.right is not None:
			return self.right.__contains__(word)
		elif word < self.word and self.left is not None:
			return self.left.__contains__(word)
		elif word == self.word:
			return True
		return False
	
	def search(self, word):
		if self.word == word:
			return self
		elif word > self.word and self.right is not None:
			return self.right.search(word)
		elif word < self.word and self.left is not None:
			return self.left.search(word)
		else:
			return None
	
	def findSubstring(self, subStr):
		words=[]
		if self.left is not None and self.left.word[:len(subStr)] >= subStr:
			words.extend(self.left.findSubstring(subStr))
		if self.word[:len(subStr)] == subStr:
			words.append(self.word)
		if self.right is not None and self.right.word[:len(subStr)] <= subStr:
			words.extend(self.right.findSubstring(subStr))
		return words
		
	def __repr__(self):
		return str({'Word':self.word , 'Meaning' : self.meaning})			
