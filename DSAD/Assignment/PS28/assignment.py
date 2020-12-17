from dijkstra import dijkstra
class IPL:
	PlayerTeam = []
	edges = [[],[]]
	franchises=[]
	players = []
	
	def __init__(self):
		self.PlayerTeam = []
		self.edges =[[],[]]
	
	def readInputFile(self, filename):
		f=open(filename,'r')
		playerList = []
		playernames = set()
		for line in f.readlines():
			line = line.replace('\n', '')
			data = line.split('/')
			self.franchises.append(data[0].strip())
			players = [datum.strip() for datum in data[1:]]
			playerList.append(players)
			playernames.update(set(players))
		self.players = list(playernames)
		self.players.sort()
		
		num_nodes = len(self.franchises) + len(self.players)
		self.edges = [[0]*num_nodes for i in range(num_nodes)]
		for index, franchise in enumerate(self.franchises):
			for player in playerList[index]:
				j = self.players.index(player) 
				self.edges[index][j+len(self.franchises)] =1
				self.edges[j+len(self.franchises)][index]=1

	
	def displayAll(self):
		print('--------Function displayAll--------')
		print('Total no. of franchises: '+str(len(self.franchises)))
		print('List of franchises: ')
		print("\n".join(self.franchises))
		print('\nList of players: ')
		print("\n".join(self.players))

	def displayFranchises(self,player):
		print('--------Function displayFranchises --------')
		print('Player name: '+player)
		print('List of Franchises: ')
		if not self.players.__contains__(player):
			print('Player not found')
		else:
			playerIndex = self.players.index(player) + len(self.franchises)
			found = False
			for i in range(len(self.franchises)):
				if self.edges[playerIndex][i] == 1:
					found = True
					print(self.franchises[i],end=' ')
			if not found:
				print('Player not associated with any franchise', end = ' ')
			print()
	
	def displayPlayers(self,franchise):
		print('--------Function displayPlayers --------')
		print('Franchise name: '+franchise)
		if not self.franchises.__contains__(franchise):
			print('Franchise not found')
		else:
			franchiseIndex = self.franchises.index(franchise)
			found = False
			for i in range(len(self.players)):
				j = i+len(self.franchises)
				if self.edges[franchiseIndex][j] == 1:
					found = True
					print(self.players[i], end = ' ')
			if not found:
				print('Franchise not associated with any player',end=' ')
			print()
	
	
	def franchiseBuddies(self,playerA,playerB):
		p1=self.players.index(playerA) + len(self.franchises)
		p2=self.players.index(playerB) + len(self.franchises)
		print('--------Function franchiseBuddies --------')
		print('Player A: '+playerA)
		print('Player B: '+playerB)
		print('Franchise Buddies: ',end="")
		found = False
		for i in range(len(self.franchises)):
			if self.edges[p1][i] == 1 and self.edges[p2][i] == 1:
				found = True
				print('Yes, '+self.franchises[i])
		if not found:
			print('No, the players are not franchise buddies')
	
	def printPath(self,nodes, parent, i):
		if parent[i] == -1:
			print(nodes[i],end=' > ')
			return
		self.printPath(nodes, parent, parent[i])
		print(nodes[i],end=' > ')
	
	def findPlayerConnect(self, playerA, playerB):
		print('--------Function findPlayerConnect -------')
		print('Player A: '+playerA)
		print('Player B: '+playerB)
		p1 = len(self.franchises) + self.players.index(playerA)
		p2 = len(self.franchises) + self.players.index(playerB)
		parent, dist = dijkstra(self.edges, p1)
		nodes = self.franchises
		nodes.extend(self.players)
		if dist[p2] == 99999:
			print('Related: No')
		else:
			print('Related: Yes,', end=' ')
			self.printPath(nodes, parent, p2)
			print()

i=IPL()
i.readInputFile('InputPS28.txt')
i.displayAll()
i.displayFranchises('Ben Stokes')
i.displayPlayers('RR')
i.franchiseBuddies('Krunal Pandya','Kieron Pollard')
i.findPlayerConnect('Kedar Jadhav','Ishan Kishan')	
