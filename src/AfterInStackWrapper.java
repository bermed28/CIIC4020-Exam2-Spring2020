public class AfterInStackWrapper {
	public interface Stack<E> {
		public int size();
		public boolean isEmpty();
		public E top();
		public E pop();
		public void push(E e);
		public void clear();
	}

	public static class SingleLinkedStack<E> implements Stack<E> {

		// node class
		@SuppressWarnings("hiding")
		private  class Node<E> {
			private E element;
			private Node<E> next;

			@SuppressWarnings("unused")
			public Node(E element, Node<E> next) {
				super();
				this.element = element;
				this.next = next;
			}
			public Node() {
				super();
			}
			public E getElement() {
				return element;
			}
			public void setElement(E element) {
				this.element = element;
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

		public SingleLinkedStack(){
			this.header = new Node<>();
			this.currentSize =0;
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
		public E top() {
			if (this.isEmpty())
				return null;
			else
				return this.header.getNext().getElement();
		}

		@Override
		public E pop() {
			if (this.isEmpty())
				return null;
			else {
				E result = this.header.getNext().getElement();
				Node<E> temp = this.header.getNext();
				this.header.setNext(temp.getNext());
				temp.setNext(null);
				temp.setElement(null);
				this.currentSize--;
				return result;
			}
		}

		@Override
		public void push(E e) {
			Node<E> newNode = new Node<>();
			newNode.setElement(e);
			newNode.setNext(this.header.getNext());
			this.header.setNext(newNode);
			this.currentSize++;
		}

		@Override
		public void clear() {
			while (this.pop() !=null);
		}
	}

	public static Stack<String> afterInStack(Stack<String> S, String e){
		Stack<String> temp = new SingleLinkedStack<>();
		Stack<String> result = new SingleLinkedStack<>();

		if(S.isEmpty()) return result;
		while(!S.isEmpty()) {
			temp.push(S.pop());
		}

		while(!temp.top().equals(e)) {
			String popped = temp.pop();
			result.push(popped);
			S.push(popped);
		}

		while(!temp.isEmpty()) {
			S.push(temp.pop());
		}


		return result;
	}

}