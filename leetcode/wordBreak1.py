class Solution:

    # @param s, a string

    # @param dict, a set of string

    # @return a boolean

    def wordBreak(self, s, dict):

        l = len(s)

        ls = [False]*l

        i = 0;

        while i<l:

            if s[0:i+1] in dict:

                ls[i] = True

            else:

                j = 0

                while j < i:

                    if ls[j] is True and s[j+1:i+1] in dict:

                        ls[i] = True

                        break

                    j += 1

            i += 1

        return ls[l-1]
