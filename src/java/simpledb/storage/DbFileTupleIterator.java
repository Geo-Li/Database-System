package simpledb.storage;

import java.util.*;

import simpledb.common.DbException;
import simpledb.transaction.TransactionAbortedException;

public class DbFileTupleIterator extends AbstractDbFileIterator {

	private boolean isClosed;
	private ArrayList<Tuple> tuples;
	private Iterator<Tuple> tuplesItr;
	
	public DbFileTupleIterator(ArrayList<Tuple> tuples) {
		this.tuples = tuples;
		tuplesItr = tuples.iterator();
	}
	
	@Override
	public void open() throws DbException, TransactionAbortedException {
		tuplesItr = tuples.iterator();
		isClosed = false;
	}
	
	/**
     * Closes the iterator.
     */
	@Override
    public void close() {
		tuplesItr = tuples.iterator();
		isClosed = true;
	}
	
	@Override
	public boolean hasNext() {
		if (isClosed) {
    		throw new IllegalStateException();
    	}
		return tuplesItr.hasNext();
	}

	@Override
	public void rewind() throws DbException, TransactionAbortedException {
		if (isClosed) {
    		throw new IllegalStateException();
    	}
		tuplesItr = tuples.iterator();
	}

	@Override
	public Tuple next() {
		if (isClosed) {
    		throw new IllegalStateException();
    	}
		try {
			return tuplesItr.next();
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	protected Tuple readNext() throws DbException, TransactionAbortedException {
		if (isClosed) {
    		throw new IllegalStateException();
    	}
		return this.next();
	}

}
