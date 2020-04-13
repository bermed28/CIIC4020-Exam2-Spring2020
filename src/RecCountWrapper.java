public class RecCountWrapper {
	public static interface List<E> {
		
		public int size();
		public boolean isEmpty();
		public boolean isMember(E e);
		public int firstIndexOf(E e);
		public int lastIndexOf(E e);
		public void add(E e);
		public void add(E e, int index);
		public E get(int index);
		public E remove(int index);
		public boolean remove(E e);
		public int removeAll(E e);
		public E replace(int index, E newElement);
		public void clear();
		public Object[] toArray();
		public int count(E e);
	}
	
	public static class SinglyLinkedList<E> implements List<E> {

		private static class Node<E> {
			private E value;
			private Node<E> next;
			
			public Node(E value, Node<E> next) {
				super();
				this.value = value;
				this.next = next;
			}
			public Node() {
				super();
			}
			public E getValue() {
				return value;
			}
			public void setValue(E value) {
				this.value = value;
			}
			public Node<E> getNext() {
				return next;
			}
			public void setNext(Node<E> next) {
				this.next = next;
			}
		}

		private Node<E> header;
		private int currentSize;
		
		public SinglyLinkedList() {
			this.header = new Node<>();
			this.currentSize = 0;
		}
		
		@Override
		public int size() {
			return this.currentSize;
		}

		@Override
		public boolean isEmpty() {
			return this.size() == 0;
		}

		@Override
		public boolean isMember(E e) {
			return this.firstIndexOf(e) >= 0;
		}

		@Override
		public int firstIndexOf(E e) {
			int i = 0;
			for (Node<E> temp = this.header.getNext(); temp != null; 
					temp = temp.getNext(), ++i) {
				if (temp.getValue().equals(e))
					return i;
			}
			// not found
			return -1;
		}

		@Override
		public void add(E e) {
			if (this.isEmpty()) {
				this.header.setNext(new Node<E>(e, null));
				this.currentSize++;
			}
			else {
				Node<E>temp= this.header.getNext();
				while (temp.getNext() != null)
					temp = temp.getNext();
				Node<E> newNode = new Node<>(e, null);
				temp.setNext(newNode);
				this.currentSize++;
			}
		}

		@Override
		public void add(E e, int index) {
			if ((index < 0) || (index > this.currentSize))
				throw new IndexOutOfBoundsException();
			if (index == this.currentSize)
				this.add(e);
			else {
				Node<E> temp = null;
				if (index == 0)
					temp = this.header;
				else
					temp = this.getPosition(index -1);
				Node<E> newNode = new Node<>();
				newNode.setValue(e);
				newNode.setNext(temp.getNext());			
				temp.setNext(newNode);
				this.currentSize++;
			}
		}

		@Override
		public E get(int position) {
			if ((position < 0) || position >= this.currentSize)
				throw new IndexOutOfBoundsException();
			
			Node<E> temp  = this.getPosition(position);
			return temp.getValue();
		}

		private Node<E> getPosition(int index){
			int currentPosition=0;
			Node<E> temp = this.header.getNext();
			
			while(currentPosition != index) {
				temp = temp.getNext();
				currentPosition++;
			}
			return temp;
		}
		
		@Override
		public E remove(int index) {
			if ((index < 0) || (index >= this.currentSize))
				throw new IndexOutOfBoundsException();
			else {
				Node<E> temp = this.header;
				int currentPosition =0;
				Node<E> target = null;
				
				while (currentPosition != index) {
					temp = temp.getNext();
					currentPosition++;
				}
				E result = null;
				target = temp.getNext();
				result = target.getValue();
				temp.setNext(target.getNext());
				target.setValue(null);
				target.setNext(null);
				this.currentSize--;
				return result;
			}
		}

		@Override
		public E replace(int position, E newValue) {
			if ((position < 0) || position >= this.currentSize)
				throw new IndexOutOfBoundsException();
			Node<E> temp  = this.getPosition(position);
			E result = temp.getValue();
			temp.setValue(newValue);
			return result;
		}

		@Override
		public void clear() {
			while(!this.isEmpty())
				this.remove(0);
		}

		@Override
		public Object[] toArray() {
			Object[] result = new Object[this.size()];
			for (int i=0; i < this.size(); ++i)
				result[i] = this.get(i);
			return result;
		}

		@Override
		public int lastIndexOf(E e) {
			int i = 0, result = -1;
			for (Node<E> temp = this.header.getNext(); temp != null; 
					temp = temp.getNext(), ++i) {
				if (temp.getValue().equals(e))
					result = i;
			}
			// not found
			return result;
		}

		@Override
		public boolean remove(E e) {
			int i = this.firstIndexOf(e);
			if (i < 0)
				return false;
			else {
				this.remove(i);
				return true;
			}
		}

		@Override
		public int removeAll(E e) {
			int count = 0;
			while (this.remove(e))
				count++;
			return count;
		}

		@Override
		public int count(E e) {
			/* ADD YOUR CODE HERE */
			/* This method should make use of the recCount auxiliary recursive method */
			return recCount(header.getNext(), e);
		}

		/**
		 * Auxiliary recursive method to count the number of ocurrences of e
		 */
		private int recCount(Node<E> first, E e) {
			/* ADD YOUR CODE HERE */
			int count = 0;
			if(first == null) return count;
			if(first.getValue().equals(e)) {
				count++;
			}
			
			return count + recCount(first.getNext(), e);
		}

	}
}