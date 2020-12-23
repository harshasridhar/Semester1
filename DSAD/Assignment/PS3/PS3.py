f=open('InputPS3.txt')
output=open('Output.txt','w')
lines=f.readlines()
lines = [line.replace('\n','') for line in lines]
n = int(lines[0].strip())
queue=[int(i) for i in lines[1].strip().split(" ")]  
max_element = n
min_element = n
visited = []
for i in range(n):
	visited.append(queue[i])
	output.write(str(i+1)+'> ')
	if queue[i] < min_element:
		min_element = queue[i]
	if queue[i] == max_element:
		last_accessed = 0
		for j in reversed(range(min_element, max_element + 1)):
			if j in visited:
				last_accessed = j
				output.write(str(j) +' ')
			else:
				break
		if i != n-1:
			output.write('\n')
		max_element = last_accessed - 1
	else:
		output.write('\n')
output.close()
