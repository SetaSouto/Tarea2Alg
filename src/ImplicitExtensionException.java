class ImplicitExtensionException extends Exception {
    /**
     * Default constructor.
     *
     * @param s the string which is implicitly extending the suffix tree.
     */
    ImplicitExtensionException(String s) {
        super("Implicit extension for " + s);
    }
}
