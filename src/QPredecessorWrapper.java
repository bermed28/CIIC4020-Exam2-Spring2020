import java.io.PrintStream;

public class QPredecessorWrapper {

	public interface Queue<E> {
		public int size();
		public boolean isEmpty();
		public E front();
		public void enqueue(E e);
		public E dequeue();
		public void makeEmpty();
		public void print(PrintStream P);
	}

	public static class DoublyLinkedQueue<E> implements Queue<E> {

		private static class Node<E>{
			private E element;
			private Node<E> next, prev;

			public Node() {
				this.element = null;
				this.next = this.prev = null;

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
			public Node<E> getPrev() {
				return prev;
			}
			public void setPrev(Node<E> prev) {
				this.prev = prev;
			}
		}

		private Node<E> header, trailer;
		private int currentSize;

		public DoublyLinkedQueue() {
			this.currentSize = 0;
			this.header = new Node<>();
			this.trailer = new Node<>();
			this.header.setNext(this.trailer);
			this.trailer.setPrev(this.header);
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
		public E front() {
			return (this.isEmpty() ? null : this.header.getNext().getElement());
		}

		@Override
		public E dequeue() {
			if (this.isEmpty())
				return null;
			else {
				Node<E> target = null;
				target = this.header.getNext();
				E result = target.getElement();
				this.header.setNext(target.getNext());
				target.getNext().setPrev(this.header);
				target.setNext(null);
				target.setPrev(null);
				target.setElement(null);
				this.currentSize--;
				return result;
			}
		}

		@Override
		public void enqueue(E e) {
			Node<E> newNode = new Node<E>();
			newNode.setElement(e);
			newNode.setNext(this.trailer);
			newNode.setPrev(this.trailer.getPrev());
			this.trailer.setPrev(newNode);
			newNode.getPrev().setNext(newNode);
			this.currentSize++;
		}

		@Override
		public void makeEmpty() {
			while (this.dequeue() != null);

		}

		@Override
		public void print(PrintStream P) {
			Node<E> temp = this.header.getNext();
			while(temp != this.trailer) {
				P.println(temp.getElement());
				temp = temp.getNext();
			}
		}
	}

	public static <E> E predecessor(Queue<E> Q, E e) {
		E value = null;
		int i = 0;
		if(Q.front() != e) {
			while(i < Q.size()) {
				E deq = Q.dequeue();
				if(Q.front() != e) {
					Q.enqueue(deq);
				} else {
					value = deq;
					Q.enqueue(deq);
				}
			
				i++;
				
			}
		}
		
		return value;
		
	}
}