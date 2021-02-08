# This algorithm takes an array with n integers and returns the max sum
# continued elements in the array

"""
input:
    firstline - n(number of elements)
    line 2: 1st element
    line 3: 2nd element
    ...
    line n+1: n-th element

Output: max sum of continued elements
"""


def OPT(l):
    sum_list = []
    pos_sum = 0
    neg_sum = 0
    index = -1
    for i in l:
        index += 1
        if i >= 0:
            if neg_sum != 0:
                sum_list.append(neg_sum)
                neg_sum = 0
            pos_sum += i
            if index == len(l)-1:
                sum_list.append(pos_sum)
        else:
            if pos_sum != 0:
                sum_list.append(pos_sum)
                pos_sum = 0
            neg_sum += i
            if index == len(l)-1:
                sum_list.append(neg_sum)

    max_sum = max(sum_list)
    if max_sum <= 0:
        return 0
    max_index = sum_list.index(max_sum)

    left_max = 0
    right_max = 0

    # search left part if max_sum is not on the left-most
    if max_index != 0:
        left_sum = []
        temp = 0
        for i in reversed(sum_list[:max_index]):
            temp += i
            if i >= 0:
                left_sum.append(temp)
        if len(left_sum) != 0:
            left_max = max(left_sum)
            if left_max < 0:
                left_max = 0

    # search right part if max if max_sum is not on the right-most
    if max_index != len(sum_list):
        right_sum = []
        temp = 0
        for i in sum_list[max_index+1:]:
            temp += i
            if i >= 0:
                right_sum.append(temp)
        if len(right_sum) != 0:
            right_max = max(right_sum)
            if right_max < 0:
                right_max = 0

    return max_sum+right_max+left_max


def main():
    n = int(input())
    l = []
    for i in range(n):
        l.append(int(input()))
    print(OPT(l))


if __name__ == '__main__':
    main()
