class Node:
	'''
		Node represents a node in the Binary Search Tree
		functions:
			addNode(self,value) - adds a node to the BST with data = value
			printTree(self, order) - prints the BST, 
				order = "in" by default, prints BST in inorder form
				order = "pre" prints BST in preorder form
				order = "post" prints BST in postorder form
				order = "revin" prints the BST in reverse inorder format i.e Right->Root->Left
	'''
	def __init__(self, value):
		'''
			__init__(self,value) - creates an instance of Node with the given value
		'''
		self.left = None
		self.right = None
		self.value = value
	
	def addNode(self, value):
		'''
			addNode(self, value) - adds a Node with the given value
				If the tree has no nodes, the node is created as root
				If the value of new node > root node value, recursively call the function to add it as a right child
				If the value of new node < root node value, recursively call the function to add it as a left child
		'''
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
	def printTree(self,order = "in"):
		'''
			printTree(self,order) - prints the BST
				order = "in"(default value) prints the BST in inorder format
				order = "pre" prints the BST in preorder format
				order = "post" prints the BST in postorder format
				order = "revin" prints the BST in reverse inorder format i.e Right->Root->Left
		'''
		if order == "in":
			if self.left is not None:
				self.left.printTree(order)
			print(self.value, end = " ")
			if self.right is not None:
				self.right.printTree(order)
		elif order == "pre":
			print(self.value, end=" ")
			if self.left is not None:
				self.left.printTree(order)
			if self.right is not None:
				self.right.printTree(order)
		elif order == "post":
			if self.left is not None:
				self.left.printTree(order)
			if self.right is not None:
				self.right.printTree(order)
			print(self.value, end =" ")
		elif order == "revin":
			if self.right is not None:
				self.right.printTree(order)
			print(self.value, end=" ")
			if self.left is not None:
				self.left.printTree(order)
	
	def getTreeRepresentation(self, order="in"):
		'''
			getTreeRepresentation(self,order) - gets the tree representation of BST
				order = "in"(default value) gets the tree representation of BST in inorder format
				order = "pre" gets the tree representation of BST in preorder format
				order = "post" gets the tree representation of BST in postorder format
				order = "revin" gets the tree representation of BST in reverse inorder format i.e Right->Root->Left
		'''
		treeRepr = ""
		if order == "in":
			if self.left is not None:
				treeRepr += self.left.getTreeRepresentation(order)
			treeRepr += str(self.value) + " "
			if self.right is not None:
				treeRepr += self.right.getTreeRepresentation(order)
		elif order == "pre":
			treeRepr += str(self.value) + " "	
			if self.left is not None:
				treeRepr += self.left.getTreeRepresentation(order)
			if self.right is not None:
				treeRepr += self.right.getTreeRepresentation(order)
		elif order == "post":
			if self.left is not None:
				treeRepr += self.left.getTreeRepresentation(order)
			if self.right is not None:
				treeRepr += self.right.getTreeRepresentation(order)
			treeRepr += str(self.value) + " "
		elif order =="revin":
			if self.right is not None:
				treeRepr += self.right.getTreeRepresentation(order)
			treeRepr+= str(self.value) + " "
			if self.left is not None:
				treeRepr += self.left.getTreeRepresentation(order)	
		return treeRepr

	def __contains__(self, value):
		'''
			__contains__(self, value) - returns True if the value is present in the BST else returns False
		'''
		if value > self.value and self.right is not None:
			return self.right.__contains__(value)
		elif value < self.value and self.left is not None:
			return self.left.__contains__(value)
		return self.value == value

class PS06:
	def __init__(self):
		self.increasingTree = None
		self.decreasingTree = None
		self.max_element = None
		self.N = 0
		self.incrCount = 0
		self.decrCount = 0

	def readInput(self, filename):
		'''
			def readInput(self, filename):
				This function reads a file with name given by the filename and returns the initial BSequence, number of new elements and new elements
		'''
		inputFile = open(filename)
		lines=inputFile.readlines()
		lines=[line.replace("\n","").strip() for line in lines]
		inputFile.close()
		try:
			self.N=int(lines[0])
			S=[int(i) for i in  lines[1].split(" ")]
			if self.N != len(S):
				raise Exception('Given size of elements in B-sequence do not match with actual size')
			Q=int(lines[2])
			Q_elements =[int(line.replace("\n","").strip()) for line in lines[3:]]
			if Q != len(Q_elements):
				raise Exception('given size of new elements do not match with the actual size')
			return S,Q,Q_elements
		except ValueError:
			raise Exception('Input values are not acceptable!')
	
	def initialize(self,input_b_sequence):
		'''
			def initialize(self, input_b_sequence):
				This function builds an increasingTree and a decreasingTree out of the given input B-Sequence
		'''
		self.max_element = max(input_b_sequence)
		max_element_index = input_b_sequence.index(self.max_element)
		for i in input_b_sequence[:max_element_index]:
			if self.increasingTree is None:
				self.increasingTree = Node(i)
			else:
				self.increasingTree.addNode(i)
			self.incrCount+=1
		for i in input_b_sequence[max_element_index+1:]:
			if self.decreasingTree is None:
				self.decreasingTree = Node(i)
			else:
				self.decreasingTree.addNode(i)
			self.decrCount+=1
	
	def addElementsToSequenceAndOutputResultToFile(self, elements_to_add, filename):
		'''
			def addElementsToSequenceAndOutputResultToFile(self, elements_to_add, filename):
				This function adds elements to the extisting B-Sequence, and writes the output to a file
		'''
		outputFile = open(filename, 'w')
		for i in range(len(elements_to_add)):
			new_element = elements_to_add[i]
			if new_element == self.max_element:
				pass
			elif new_element > self.max_element:
				self.increasingTree.addNode(self.max_element)
				self.incrCount+=1
				self.max_element = new_element
			elif self.increasingTree is not None and new_element in self.increasingTree:
				if self.decreasingTree is None:
					self.decreasingTree = Node(new_element)
					self.decrCount += 1
				elif new_element in self.decreasingTree:
					pass
				else:
					self.decreasingTree.addNode(new_element)
					self.decrCount+=1
			else:
				self.increasingTree.addNode(new_element)
				self.incrCount += 1
			outputFile.write(str(self.incrCount+1+self.decrCount)+"\n")
		new_node=Node(self.max_element)
		new_node.left = self.decreasingTree
		self.decreasingTree = new_node
		if self.increasingTree is not None:
			outputFile.write(self.increasingTree.getTreeRepresentation().strip()+ ' ')
		if self.decreasingTree is not None:
			outputFile.write(self.decreasingTree.getTreeRepresentation('revin').strip()+ ' ')
		outputFile.write('\n')
		outputFile.close()


def main():
	problem = PS06()
	inputFilename = 'inputPS6.txt'
	outputFilename = 'outputPS6.txt'
	try:
		S,Q,Q_elements = problem.readInput(inputFilename)
		problem.initialize(S)
		problem.addElementsToSequenceAndOutputResultToFile(Q_elements, outputFilename)
	except Exception as error:
		print('Error occurred:\n\t'+repr(error))


if __name__ == '__main__':
	main()