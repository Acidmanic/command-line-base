package com.acidmanic.commandline.argumentparsing;



public class ArgumentParser {
    /**
     *
     * @return This function will concatenate all arguments together separating
     * with a space.
     */
    public String allArgsAsCommand(String[] args) {
        return allArgsAsCommand(args,0);
    }

    /**
     *
     * @param startIndex
     * @return This function will concatenate all arguments, starting from (and
     * including) <code>startIndex</code>.
     */
    public String allArgsAsCommand(String[] args,int startIndex) {
        String ret = "";
        String sep = "";
        for (int i = startIndex; i < args.length; i++) {
            ret += sep + args[i];
            sep = " ";
        }
        return ret;
    }
}