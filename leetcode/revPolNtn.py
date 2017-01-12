class Solution:

    # @param tokens, a list of string

    # @return an integer

    

    def evalRPN(self, tokens):

            st = []

            for s in tokens:

                #print s

                if s == '*' or s == '/' or s == '+' or s == '-':

                    sec = int(st.pop())

                    fst = int(st.pop())

                    sign = 1

                    if s == '*':

                        if sec < 0:

                            sign *= -1

                        if fst < 0:

                            sign *= -1

    

                        res = math.fabs(sec) * math.fabs(fst) * sign

                    elif s == '-':

                        res = fst - sec

                    elif s == '/':

                        if sec < 0:

                            sign *= -1

                        if fst < 0:

                            sign *= -1

    

                        res = math.fabs(fst)/math.fabs(sec) * sign

                        #print fst, sec, res

                    else:

                        res = sec + fst

                    st.append(res)

                else:

                    st.append(s)

                #print st

            return int(st[0])
