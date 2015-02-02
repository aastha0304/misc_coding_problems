"""
This code evaluates the maximum profit made from stock prices when travelling forwards.
Backwards traversal can do this linearly but that would not solve the purpose of stock profits in real time

Here the idea is to push elements to stack as they appear and pop them if they are smaller than current day's
stock price, get the sum of elements that were popped and add it to sum so far"""


class Stack:
    def __init__(self):
        self.items = []

    def isEmpty(self):
        return self.items == []

    def push(self, item):
        self.items.append(item)

    def pop(self):
        return self.items.pop()

    def peek(self):
        return self.items[len(self.items)-1]

    def size(self):
        return len(self.items)

"""function gets span of stock prices that is the number of days before current day for which stock price
is less  than current days"""
def max_span(stocks):
    lsums = []
    stack = Stack()
    stack.push(0)
    lsums.append(0)
    for e, stock in enumerate(stocks):
        if e == 0:
            continue
        while not stack.isEmpty() and stocks[stack.peek()] < stock:
            stack.pop()
        if stack.isEmpty():
            lsums.append(e)
        else:
            lsums.append(e-stack.peek()-1)
        stack.push(e)
    return lsums

"""function to get daily profit on stock prices by building up on the span idea"""
def max_profit(stocks):
    lsums = []
    stack = Stack()
    stack.push(0)
    stacksum = 0
    lsums.append(0)
    for e, stock in enumerate(stocks):

        if e == 0:
            continue
        while not stack.isEmpty():
            if stocks[stack.peek()] < stock:
                stacksum += stocks[stack.peek()]
                stack.pop()
            else:
                if e-stack.peek() <= 1:
                    stacksum = 0
                break
        if stack.isEmpty():
            lsums.append(e*stock-stacksum)
        else:
            lsums.append((e-stack.peek()-1)*stock - stacksum)
        stack.push(e)
    return lsums

"""function ges total profit made once daily profits are evaluated"""
def total(arr, l):
    s = 0
    i = 1
    while i < l:
        if arr[i] != 0:
            j = i
            last_max = arr[i]
            while j < l and arr[j] != 0:
                last_max = arr[j]
                j += 1
            s += last_max
            i = j
        else:
            i += 1
    return s


def main():
    stocks = [6, 3, 0, 3, 6, 7, 7, 5, 3, 1, 2]
    l = len(stocks)
    max_till_n_day = max_profit(stocks)
    print max_span(stocks)
    print max_till_n_day
    print(total(max_till_n_day, l))
if __name__ == '__main__':
    main()
