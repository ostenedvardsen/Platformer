###Excercise 00:
The vulnerability: the buffer is 16 characters , while fgets takes in a much larger amount. This means that we can send in something larger than what has been allocated for buffer in the stack. Our goal is to change locals.check from 0xabcdc3cf to 0x79beef8b so that the if statement passes. Personally i used gdb and the command "x/ 20 $sp" to view the stack. Here we can see 0xabcdc3cf and a row of zeroes, aka the buffer. So our input has to first fill the 16 characters of the buffer, and then override locals.check with 0x79beef8b. I used p64() from pwntools to add 0x79beef8b as a bytes object.
<br>
<b>We then receive the flag: INF226{s3cR3t_f1Agz}.</b>


###Excercise 01:
Once again we have a buffer that is smaller than what fgets takes in, so we can have a stack overflow buffer attack. funcPointer is currently pointing at NULL, but we want it to point at getFlag(). funcPointer is  also volatile, which means that the compiler will always assume that the value that funcPointer is pointing at might have changed, even if nothing in the code implies that it has changed. Using "objdump -dissassemble 01" we can find the pointer address of "cat flag". We now do like we did in excercise00 to send in and input of 16 characters to fill the buffer and also the memory address that we found using objdump. stores.funcPointer is no longer NULL, so the if statement passes. stores.funcPointer() is then called and is now pointing at cat flag: 
<br>
<b> so we get the flag: INF226{2b_0r_!2b}. </b>


###Excercise 02:
This program has a stack canary, which is a random integer placed just before the stack return pointer. If it is overwritten in a stack buffer overflow, it will terminate the process. In this excercise we want to overwrite the stack return pointer, but in order to do so we first have to find out the canary value.
<br>
The first input value of the program is saved to &offset. Offset is later used in line 21:
printf("%lx\n", *(unsigned long*)(buffer+offset));
<br>
*(unsigned long*)(buffer+offset)<br>
From what i can understand of C, this line will first perform buffer + offset, which will take the memory address of buffer and add the int value that is stored in offset. (unsigned long*) then typecasts this to a pointer to unsigned long. The first * dereferences it to assign its value to whatever variable printf uses.
<br>
Using this we can make the printf() give us the canary. When our first input is 0 we get the output 706050403020100. This is the beginning of the buffer. When our first input is 16 we get past the buffer. When the input is 24 we hit the canary. At values between 16 and 24 can be pretty confusing and it is sometimes less than 16 symbols, but i think that comes down to how the program, server or my script treats zeroes. Between the buffer and the canary there always seems to be some value 00007ffc.
<br>
The last piece we need is where we want the stack return pointer to go. Using objdump -dissassemble 02 we get the memory address of cat flag (0x0000000000401257).
<br>
Now that we finally have the canary we can get to stack buffer overflowing the second input. We have to send in 16 ints (to get past the buffer), 8 ints (to get past the offset between the buffer and the canary), the canary (to make the program think that we haven't changed the canary) and then the memory address of cat flag (where we want the stack return pointer to go).
<br>
<b>We then get the flag: INF226{s3r1nu5_cAnar1a}.</b>



Tools/libraries:
    pwntools
    gdb
