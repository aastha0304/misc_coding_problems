import time

#The following code assumes that each node has only one parent
#The idea is to store parent of each Node, as well as level of each known for computation
#The above is done by 'levels' dictionary
#The 'children' dictionary stores first degree children of the node.
#This dictionary will be useful for rendering
#for sample input
#video games\nintendo
#electronics\video games\wii
#everything else\office supplies
#electronics\tabs\apple
#apple\ipad
#electronics\video games\consoles\wii
#The code outputs tab separated structure like this
#home
#        electronics
#                video games
#                        nintendo
#                        consoles
#                                wii
#                tabs
#                        apple
#                                ipad
#        everything else
#                office supplies


#for checking time taken for functions
def timeit(method):
    def timed(*args, **kw):
        ts = time.time()
        result = method(*args, **kw)
        te = time.time()
        print '%r %2.2f sec' % \
              (method.__name__, te-ts)
        return result
    return timed


def add_to_children(children, parent, item):
    if parent not in children:
        children[parent] = []

    children[parent].append(item)


def handle_dcs(levels, children, item, level, parent):
    if item in levels:
        if levels[item]['level'] < level:
            levels[item]['level'] = level
            op = levels[item]['parent']
            levels[item]['parent'] = parent
            children[op].remove(item)  # could be slow since list is actually be array, can substitute with
            # double linked list
            add_to_children(children, parent, item)

    else:
        levels[item] = {'level': level, 'parent': parent}
        add_to_children(children, parent, item)


def parse(ip):
    levels = {'home': {'level': 0, 'parent': None}}
    children = {'home': []}
    for line in ip:
            #print line
            parent = 'home'
            item = ''
            i = 0
            for ch in line:  # have not used split() since that would cause 2 traversals
                if ch != '/' and ch != '\n':
                    item += ch
                else:
                    handle_dcs(levels, children, item, levels[parent]['level']+1, parent)
                    i += 1
                    parent = item
                    item = ''
    return levels, children


def get_tabs(tabs):
    i = 0
    s = ''
    while i < tabs:
        s += '\t'
        i += 1
    return s


def print_pretty(children, key, tabs):
    spaces = get_tabs(tabs)
    print spaces+key
    if key in children:
        for child in children[key]:
            print_pretty(children, child, tabs+1)

'''
def main():
    ip = '/home/aastha/catree.txt'
    levels, children = parse(ip)
    print_pretty(children, 'home', 0)
if __name__ == '__main__':
    main()'''