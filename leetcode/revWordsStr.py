class Solution:

    # @param s, a string

    # @return a string

    def reverseWords(self, s):

        s1 = s[::-1]

        s = s1.split()

        res = ''

        for w in s:

            res += w[::-1]

            res += ' '

        return res.strip()
