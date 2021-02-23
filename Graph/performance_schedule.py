# N cities, M ways
# Mon - Sat: all different
# new Mon = prv Sat
# city 1 on 1:Mon ==> N: last Sat (Few as possible)
# Output: shortest time or -1(impossible)
# Input:
#   1: N(cities) M(roads)
#   M lines: u v (u<->v && u!=v)


# Graph representation reference:
# https://www.geeksforgeeks.org/graph-and-its-representations/
class Node:
    def __init__(self, n):
        self._vertex = n
        self._next = None


class Graph:
    def __init__(self, V):
        self._numV = V
        self._graph = [None] * self._numV

    def addEdge(self, u, v):
        node = Node(v)
        node._next = self._graph[u]
        self._graph[u] = node

        node = Node(u)
        node._next = self._graph[v]
        self._graph[v] = node


def main():
    N, M = map(int, input().split())
    cityMap = Graph(N)
    for i in range(M):
        u, v = map(int, input().split())
        cityMap.addEdge(u-1, v-1)


if __name__ == '__main__':
    main()
