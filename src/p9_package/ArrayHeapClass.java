package p9_package;

/**
 * Array-based max heap class that manages integers
 * @author Nigel Grey
 */
public class ArrayHeapClass
{
    /**
     * Initial array capacity
     */
    public final int DEFAULT_ARRAY_CAPACITY = 10;

    /**
     * Constant for not found value
     */
    public final int NOT_FOUND = 99999;

    /**
     * Management data for array
     */
    private int arrayCapacity;

    /**
     * Management data for array
     */
    private int arraySize;

    /**
     * Display flag can be set to observe bubble up and trickle down operations
     */
    private boolean displayFlag;

    /**
     * Array for heap
     */
    private int[] heapArray;

    /**
     * Default constructor sets up array management conditions and default
     * display flag setting
     */
    public ArrayHeapClass()
    {
        arrayCapacity = DEFAULT_ARRAY_CAPACITY;
        arraySize = 0;
        displayFlag = false;
        heapArray = new int[ arrayCapacity ];
    }

    /**
     * Copy constructor copies array and array management conditions and
     * default display flag setting
     * @param copied ArrayHeapClass object to be copied
     */
    public ArrayHeapClass( ArrayHeapClass copied )
    {
        int arrIndex;
        arrayCapacity = copied.arrayCapacity;
        arraySize = copied.arraySize;
        displayFlag = false;
        heapArray = new int[ arrayCapacity ];

        for( arrIndex = 0; arrIndex < arraySize; arrIndex++ )
        {
            heapArray[ arrIndex ] = copied.heapArray[ arrIndex ];
        }
    }

    /**
     * Accepts integer item and adds it to heap
     * <p>
     * Note: uses bubbleUpArrayHeap to resolve unbalanced heap after data
     * addition
     * <p>
     * Note: Always checks for resize before adding data
     * @param newItem integer item to be added
     */
    public void addItem( int newItem )
    {
        System.out.println( "Adding new value: " + newItem );
        checkForResize();
        heapArray[ arraySize ] = newItem;
        bubbleUpArrayHeap( arraySize );
        arraySize++;
    }

    /**
     * Recursive operation to reset data in the correct order for the max heap
     * after new data addition
     * @param currentIndex index of current item being assessed, and moved up
     *                     as needed
     */
    private void bubbleUpArrayHeap( int currentIndex )
    {
        if( currentIndex > 0 )
        {
            int parentIndex = ( currentIndex - 1 ) / 2;
            int temp;

            if( heapArray[ currentIndex ] > heapArray[ parentIndex ] )
            {
		        if( displayFlag )
		        {
		            System.out.println( "   - Bubble up:");
		            System.out.printf(
		            "\t - Swapping parent: %d with child: %d\n",
                    heapArray[ parentIndex ], heapArray[ currentIndex ]);
		        }

                temp = heapArray[ currentIndex ];
                heapArray[ currentIndex ] = heapArray[ parentIndex ];
                heapArray[ parentIndex ] = temp;
                bubbleUpArrayHeap( parentIndex );
            }
        }
    }

    /**
     * Automatic resize operation used prior to any new data addition in the
     * heap
     * <p>
     * Note: Tests for full heap array, and resizes to twice the current
     * capacity as required
     */
    private void checkForResize()
    {
         if( arraySize == arrayCapacity )
         {
             arrayCapacity *= 2;

             int[] tempArray = new int[ arrayCapacity ];
             int arrIndex;

             for( arrIndex = 0; arrIndex < arraySize; arrIndex++ )
             {
                 tempArray[ arrIndex ] = heapArray[ arrIndex ];
             }

             heapArray = tempArray;
         }
    }

    /**
     * Tests for empty heap
     * @return boolean result of test
     */
    public boolean isEmpty()
    {
        return arraySize == 0;
    }

    /**
     * Removes integer item from top of max heap
     * <p>
     * Note: Uses trickleDownArrayHeap to resolve unbalanced heap after data
     * removal
     * @return integer item removed
     */
    public int removeItem()
    {
        if( !isEmpty() )
        {
            int removed = heapArray[ 0 ];
            System.out.println( "Removing value: " + removed );
            heapArray[ 0 ] = heapArray[ arraySize - 1 ];
            trickleDownArrayHeap( 0 );
            arraySize--;
            return removed;
        }
        return NOT_FOUND;
    }

    /**
     * Utility method to set the display flag for displaying internal
     * operations of the heap bubble and trickle operations
     * @param setState flag used to set the state to display
     */
    public void setDisplayFlag( boolean setState )
    {
        displayFlag = setState;
    }

    /**
     * Dumps array to screen as is, no filtering or management
     */
    public void showArray()
    {
        int arrIndex;

        System.out.print( "[ ");

        for( arrIndex = 0; arrIndex < arraySize; arrIndex++ )
        {
            System.out.print( heapArray[ arrIndex ] + " " );
        }

        System.out.println( "]");
    }

    /**
     * Recursive operation to reset data in the correct order for the max heap
     * after data removal
     * @param currentIndex index of current item being assessed, and moved down
     *                     as required
     */
    private void trickleDownArrayHeap( int currentIndex )
    {
        if( currentIndex < arraySize )
        {
            int rightChildIndex = 2 * ( currentIndex + 1 );
            int leftChildIndex = ( 2 * currentIndex ) + 1;
            int temp;

            if( rightChildIndex < arraySize )
            {
                if( heapArray[ rightChildIndex ] > heapArray[ currentIndex ] &&
                    heapArray[ rightChildIndex ] > heapArray[ leftChildIndex ])
                {
                    if( displayFlag )
                    {
                        System.out.println( "   - Trickle down:");
                        System.out.printf(" \t - Swapping parent:" +
                                " %d with right child: %d\n",
                                heapArray[ currentIndex ],
                                heapArray[ rightChildIndex ]);
                    }

                    temp = heapArray[ currentIndex ];
                    heapArray[ currentIndex ] = heapArray[ rightChildIndex ];
                    heapArray[ rightChildIndex ] = temp;
                    trickleDownArrayHeap( rightChildIndex );
                }
            }

            if( leftChildIndex < arraySize &&
                    heapArray[ leftChildIndex ] > heapArray[ currentIndex ] )
            {
                if( displayFlag )
                {
                    System.out.println( "   - Trickle down:");
                    System.out.printf(
                            "\t - Swapping parent: %d with left child: %d\n",
                            heapArray[ currentIndex ],
                            heapArray[ leftChildIndex ]);
                }

                temp = heapArray[ currentIndex ];
                heapArray[ currentIndex ] = heapArray[ leftChildIndex ];
                heapArray[ leftChildIndex ] = temp;
                trickleDownArrayHeap( leftChildIndex );
            }
        }
    }
}
