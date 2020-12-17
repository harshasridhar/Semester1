import sys
l=["GL","KKR","KXIP","RCB","AT","MS","KR","DK","U"]
def minDistance(dist, visited):
	min = 999999
	for i in range(len(dist)):
		if dist[i] < min and visited[i] == False:
			min = dist[i]
			min_index = i
	return min_index

def printPath(parent,i):
	if parent[i] == -1:
		print(l[i],end =' ')
		return
	printPath(parent, parent[i])
	print(l[i], end =' ')

def dijkstra(adj, src):
	dist = [99999] * len(adj)
	dist[src] = 0
	visited = [False] * len(adj)
	parent = [-1] * len(adj)
		
	for i in range(len(adj)):
		u = minDistance(dist, visited)
		visited[u] = True
		for v in range(len(adj)):
			if adj[u][v] !=0 and visited[v] == False and dist[v] > dist[u] + adj[u][v]:
				dist[v] = dist[u] + adj[u][v]
				parent[v] = u
	return parent, dist 	
			

'''
adj = [[1,0,0,1,0],[0,1,0,1,0],[1,0,1,0,0],[0,1,1,0,1]]
n_rows = len(adj)
n_cols = len(adj[0])
matrix_transpose = [[adj[j][i] for j in range(len(adj))] for i in range(len(adj[0]))]
for i,row in enumerate(adj):
	for j in range(n_rows):
		row.insert(0,0)
	adj[i]=row
adj.extend(matrix_transpose)
for i in range(n_rows, len(adj)):
	for j in range(n_cols):
		adj[i].append(0)
parent = [-1]*len(adj)
dijkstra(adj, 5)'''
