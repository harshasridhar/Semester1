inputFile = open('InputPS4.txt','r')
fileLines = inputFile.readlines()
n = int(fileLines[0])
calling = [int(i) for i in fileLines[1].split(" ")]
ideal = [int(i) for i in fileLines[2].split(" ")]
i=0
counter=0
while len(calling) != 0:
	cur = calling.pop(0)
	if cur != ideal[i]:
		calling.append(cur)
	else:
		i+=1
	counter+=1
print(counter)
