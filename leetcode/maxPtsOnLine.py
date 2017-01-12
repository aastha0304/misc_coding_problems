# Definition for a point

# class Point:

#     def __init__(self, a=0, b=0):

#         self.x = a

#         self.y = b



class Solution:

    def slope(self, p, q):

            return (p.y-q.y)/float((p.x-q.x))



        # @param points, a list of Points

        # @return an integer

    def slope(self, p, q):

            r = (p.y-q.y)/float((p.x-q.x))

            if r == 0:

                return 0

            return r



        # @param points, a list of Points

        # @return an integer

    def maxPoints(self, points):

            l = len(points)

            if l <= 2:

                return len(points)

            mx = 0

            i = 0

            while i < l:

                j = i + 1

                c = 1

                lm = 1

                hsh = {}

                while j < l:

                        p = points[i]

                        q = points[j]

                        s = '#'

                        if p.x == q.x:

                            if p.y == q.y:

                                c += 1

                                #print p.x, p.y, q.x, q.y, c

                            else:

                                s = '@'

                                #print p.x, p.y, q.x, q.y, s

                        else:

                            s = self.slope(p, q)

                            #print p.x, p.y, q.x, q.y, s

                        if s != '#':

                            k = str(p.x)+str(p.y)+str(s)

                            if k in hsh:

                                hsh[k] += 1

                            else:

                                hsh[k] = 2

                            if lm < hsh[k]:

                                lm = hsh[k]

                        j += 1

                #print hsh, c

                if mx < c+lm:

                    mx = c + lm - 1

                i += 1

            return mx
