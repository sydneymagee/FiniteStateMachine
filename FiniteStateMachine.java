package solution;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FiniteStateMachine
{
    private final static int[][] STATE_TABLE = {
        {4, 1, 0, 8}, //0
        {0, 1, 2, 8}, //1
        {0, 3, 0, 8}, //2
        {3, 3, 3, 8}, //3 ACCEPT
        {6, 5, 0, 8}, //4 
        {0, 1, 6, 8}, //5
        {4, 5, 7, 8}, //6 
        {7, 7, 7, 8}, //7 ACCEPT
        {8, 8, 8, 8}, //TRAP
    };
    
    private BufferedReader in;

    public FiniteStateMachine()
    {
        in = new BufferedReader(new InputStreamReader(System.in));
    }

    public void run() throws IOException
    {
        char ch;
        int state;
        for (;;)
        {
            System.out.print("Enter your string: ");
            ch = (char) in.read();

            // set state to start state
            state = 0;
            while (ch != '\n' && ch != '\r')
            {
                state = STATE_TABLE[state][charToColumn(ch)];
                ch = (char) in.read();
            }
            if (ch == '\r')
            {
                in.readLine();
            }
            // determine whether to accept or reject
            if (state == 3 || state == 7)
            {
                System.out.println("Accept\n");
            }
            else
            {
                System.out.println("Reject\n");
            }
        }
    }

    public int charToColumn(char ch)
    {
        // column 2 is for some unexpected character
        int column = 3;

        switch (ch)
        {
            case 'a':
                column = 0;
                break;
            case 'b':
                column = 1;
                break;
            case 'c':
                column = 2;
                break;
        }
        return column;
    }

    public static void main(String[] args)
    {
        try
        {
            FiniteStateMachine fsm = new FiniteStateMachine();
            fsm.run();
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
            System.exit(1);
        }
    }
}

