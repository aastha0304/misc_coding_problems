from bitarray import bitarray

#This code takes inpt binary string as a bitarray
#Then it does an 'or' to get the common answers known
#Then it does n*(n-1) comparisons to get maximum common answer team
def process(n):
    k = n
    array_bit = []
    while n:
        s = bitarray(raw_input())
        n -= 1
        array_bit.append(s)
    #print array_bit
    m = -1
    c = 0
    for e, i in enumerate(array_bit[0:k]):
        for j in array_bit[e+1:k]:

            p = i|j
            set_bits = p.count()
            if set_bits > m:
                m = set_bits
                c = 1
            elif set_bits == m:
                c += 1

    return m, c


def main():
    a = raw_input()
    a = a.split()
    n = int(a[0])
    m = int(a[1])
    max_a, team_count = process(n)
    print max_a, team_count

if __name__ == '__main__':
    main()