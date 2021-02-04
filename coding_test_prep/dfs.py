from collections import defaultdict


class Graph:
    def __init__(self):
        self.graph = defaultdict(list)

    def addEdge(self, u, v):
        self.graph[u].append(v)

    def DFS_visit(self, v, visited):
        visited.add(v)
        print(v, end=' ')

        for adj in self.graph[v]:
            if adj not in visited:
                self.DFS_visit(adj, visited)

    def DFS(self, s):
        visited = set()
        self.DFS_visit(s, visited)


g = Graph()
g.addEdge(0, 1)
g.addEdge(0, 2)
g.addEdge(1, 2)
g.addEdge(2, 0)
g.addEdge(2, 3)
g.addEdge(3, 3)

g.DFS(2)
