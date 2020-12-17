from math import floor
def left(i):
	return 2*i
def right(i):
	return  (2*i)+1

def max_heapify(heap_size, arr,i):
	l = left(i)
	r = right(i)
	largest = 0
	if l <= heap_size-1 and arr[l] > arr[i]:
		largest = l
	else:
		largest = i
	if r <= heap_size-1 and arr[r] > arr[l]:
		largest = r	
	if largest != i:
		temp = arr[i]
		arr[i] = arr[largest]
		arr[largest] = temp
		max_heapify(heap_size,arr, largest)
h = [2,9,7,6,5,8]
h.insert(0,0)
print(h)
for i in reversed(range(1,floor((len(h)-1)/2)+1)):
	max_heapify(len(h)-1,h,i)
print(h)
heap_size = len(h)-1
for i in reversed(range(2,len(h))):
	temp = h[1]
	h[1] = h[i]
	h[i] = temp
	heap_size -= 1
	max_heapify(heap_size,h,1)
print(h)		
