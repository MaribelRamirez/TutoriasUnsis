package VO;

import java.io.InputStream;

public class PdfVO {

    /*Todo los atributos*/
    int codigopdf;
    String nombrepdf;
    String categoria;
    InputStream archivopdf;
    private byte[] archivopdf2;

    public PdfVO(int id, String name,String categoria, byte[] archivo) {
        this.codigopdf = id;
        this.nombrepdf = name;
        this.categoria = categoria;
        this.archivopdf2 = archivo;
    }

    public PdfVO() {
    }

    /*Todo los codigos get*/
    public int getCodigopdf() {
        return codigopdf;
    }

    public String getNombrepdf() {
        return nombrepdf;
    }

    public InputStream getArchivopdf() {
        return archivopdf;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }


    /*Todo los codigos set*/
    public void setCodigopdf(int codigopdf) {
        this.codigopdf = codigopdf;
    }

    public void setNombrepdf(String nombrepdf) {
        this.nombrepdf = nombrepdf;
    }

    public void setArchivopdf(InputStream archivopdf) {
        this.archivopdf = archivopdf;
    }

    /**
     * @return the archivopdf2
     */
    public byte[] getArchivopdf2() {
        return archivopdf2;
    }

    /**
     * @param archivopdf2 the archivopdf2 to set
     */
    public void setArchivopdf2(byte[] archivopdf2) {
        this.archivopdf2 = archivopdf2;
    }

}
