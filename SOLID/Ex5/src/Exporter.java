public abstract class Exporter {
    /**
     * Exports content based on the request.
     * 
     * @throws ExportException if the request cannot be fulfilled by this format.
     */
    public abstract ExportResult export(ExportRequest req);
}
