package primary;

import org.bitcoinj.core.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.PrintWriter;

public class Main 
{
	public static void main(String[] args)
	{
		File file = new File("alphabetizedbtc.txt");
		String list[] = new String[42425];
		final NetworkParameters netParams = NetworkParameters.prodNet();
		try
		{
			Scanner scanner = new Scanner(file);
			for (int i = 0; scanner.hasNextLine(); i++)
			{
				list[i] = scanner.nextLine();
			}
			scanner.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		boolean good = true;
		String prev = list[0];
		for (int i = 1; i < 42425; i++)
		{
			if (prev.compareTo(list[i]) > 0)
				good = false;
			prev = list[i];
		} 
		System.out.println("Sorting correct: " + good);
		System.out.println("Looking for collision...");
		
		int start = 0;
		int end = 42424;
		int mid;
		int i = 0;
		ECKey key;
		String pub = "";
		boolean term = false;
		while (!term)
		{
			start = 0;
			end = 42424;
			key = new ECKey();
			while (start <= end)
			{
				mid = (start + end) / 2;
				pub = key.toAddress(netParams).toBase58().trim();
				if (pub.equals(list[mid]))
				{
					System.out.println("Holy christ collision detected");
					System.out.println(key.toAddress(netParams).toBase58());
					System.out.println(key.getPrivateKeyAsWiF(netParams));
					File filed = new File("luckynumbers.txt");
					try 
					{
						PrintWriter printWriter = new PrintWriter(filed);
						printWriter.println(key.toAddress(netParams).toBase58());
						printWriter.println(key.getPrivateKeyAsWiF(netParams));
						printWriter.close();
					} 
					catch (FileNotFoundException e) 
					{
						e.printStackTrace();
					}
					term = true;
					break;
				}
				if (pub.compareTo(list[mid]) < 0)
					end = mid - 1;
				else if (pub.compareTo(list[mid]) > 0)
					start = mid + 1;
				else
				{
					System.out.println("Failure");
					System.out.println(pub.compareTo(list[mid]));
					break;
				}
			}
			i++;
			System.out.println("Attempts: " + i);
		}
	}
}
