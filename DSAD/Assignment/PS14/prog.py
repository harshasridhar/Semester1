from CustomBinarySearchTree import Node
outputFile=open('outputPS14.txt','w')
filename='ArraydictPS14.txt'
outputFile.write('--------------- Reading from file {} --------------\n'.format(filename))
f=open(filename)
lines = f.readlines()
lines = [line.replace('\n','').strip() for line in lines]
tree=None
nodeCount=0
for line in lines:
	word, meaning = line.split('/')
	if tree is None:
		tree = Node(word.strip(), meaning.strip())
	else:
		tree.addNode(word.strip(), meaning.strip())
	nodeCount+=1
outputFile.write('BST Created with {} nodes\n'.format(nodeCount))
outputFile.write('----------------------------------------------------\n')
prompts=[line.replace('\n','').strip() for line in  open('promptsPS14.txt')]
searchResults=[]
subStringResults=[]
for prompt in prompts:
	if "SearchWord" in prompt:
		word = prompt.replace("SearchWord:","").strip()
		node = tree.search(word)
		if node is not None:
			searchResults.append('{} - {}'.format(node.word, node.meaning))
		else:
			searchResults.append('{} - not found'.format(word))
	elif "SubString" in prompt:
		word = prompt.replace("SubString:","").strip()
		words = tree.findSubstring(word)
		subStringResults.append([word,",".join(words)])
outputFile.write('------------------- Search words -------------------\n')
for searchResult in searchResults:
	outputFile.write(searchResult+"\n")	
outputFile.write('----------------------------------------------------\n')
for subStringResult in subStringResults:
	outputFile.write('--------------- Sub String: {} ------------------\n'.format(subStringResult[0]))
	outputFile.write(subStringResult[1]+"\n")
outputFile.write('----------------------------------------------------\n')
outputFile.close()
