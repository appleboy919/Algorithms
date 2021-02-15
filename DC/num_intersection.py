"""
This algorithm counts the number of intersections from each lines, which is connected as below:

q --q1---q3---q2---q4---
    \     \  /    /
      \    / \  /
        \ /   \/
        / \  / \
      /    /\    \
    /    /    \   \
p --p2---p4---p1---p3---

First line contains the number of points on each line (n). The next each n line is the points for q and p.


Description: the idea is to sort one side of points with the other side (merge sort - NlogN), and count the inversion of the sorted integers
which is performed in O(NlogN) running time using the sort-count inversions in a divide and conquer way.
"""


def lists_to_dict(l1, l2):
    """
    Takes two lists and returns a dictionary with each pair of key and value from the lists

    input: l1, l2
    output: dictionary - l1 as keys, l2 as values
    """
    d = dict()
    for i in range(len(l1)):
        d[l1[i]] = l2[i]
    return d


def merge_sort_INV(list):
    """
    MERGE-SORT with counting inversions
    Running time: O(NlogN)

    input: list
    output: (INV_counts, merge-sorted list)
    """
    n = len(list)
    if n == 1:
        return (0, list)
    leftC, L = merge_sort_INV(list[:n//2])
    rightC, R = merge_sort_INV(list[n//2:])
    sorted_list = []
    left_index = right_index = 0
    cross_count = 0
    while True:
        if right_index == len(R) and left_index == len(L):
            break
        if right_index == len(R):
            sorted_list.append(L[left_index])
            left_index += 1
        elif left_index == len(L):
            sorted_list.append(R[right_index])
            right_index += 1
        elif L[left_index] <= R[right_index]:
            sorted_list.append(L[left_index])
            left_index += 1
        elif L[left_index] > R[right_index]:
            sorted_list.append(R[right_index])
            cross_count += len(L)-left_index
            right_index += 1
    return (leftC+rightC+cross_count, sorted_list)


def sort_by_keys(dict):
    sorted_values = []
    keys = list(dict.keys())

    # merge-sort the list of keys
    keys = merge_sort_INV(keys)[1]
    for i in keys:
        sorted_values.append(dict[i])
    return sorted_values


def main():
    n = int(input())
    q_list = []
    p_list = []
    for i in range(n):
        q_list.append(int(input()))
    for i in range(n):
        p_list.append(int(input()))
    pq_pairs = lists_to_dict(p_list, q_list)
    sorted_q = sort_by_keys(pq_pairs)
    print('answer:', merge_sort_INV(sorted_q)[0])


if __name__ == '__main__':
    main()
