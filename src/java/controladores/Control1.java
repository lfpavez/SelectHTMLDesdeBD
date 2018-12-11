package controladores;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.conexion.ConexionBD;
import modelo.dto.ComunaDTO;
import modelo.dto.RegionDTO;

/**
 *
 * @author luispavezleon
 */
public class Control1 extends HttpServlet {

    private static final ConexionBD con = ConexionBD.estadoConexion();
    private static final String SQL_LEE_TODAS_REGIONES = "SELECT * FROM region";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String instruccion = request.getParameter("instruccion");
        StringBuilder toJSon;
        Integer codReg = Integer.parseInt(request.getParameter("region"));

        switch (instruccion) {

//            
            case "cargarLasRegiones":

                List<RegionDTO> listReg = llenarListaRegionesBD();

                toJSon = new StringBuilder();
                toJSon.append("[");

                Integer largoLista = listReg.size();

                for (int i = 0; i < largoLista; i++) {

                    toJSon.append("{")
                            .append("\"codigoRegion\":\"").append(listReg.get(i).getCodigo()).append("\",")
                            .append("\"nombreRegion\":\"").append(listReg.get(i).getNombreRegion()).append("\"");

                    if (i < largoLista - 1) {
                        toJSon.append("},");
                    } else {
                        toJSon.append("}]");
                    }

                }

                response.setContentType("aplication/json;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {
                    out.println(toJSon);

                }
                break;

            case "cargarLasComunas":

                List<ComunaDTO> listCom = llenarListaComunasBD(codReg);

                toJSon = new StringBuilder();

                Integer largoListaCom = listCom.size();

                if (!listCom.isEmpty()) {
                    toJSon.append("[");

                    for (int i = 0; i < largoListaCom; i++) {

                        toJSon.append("{")
                                .append("\"codigoCom\":\"").append(listCom.get(i).getCodigo()).append("\",")
                                .append("\"nombreCom\":\"").append(listCom.get(i).getNombreComuna()).append("\"");

                        if (i < largoListaCom - 1) {
                            toJSon.append("},");
                        } else {
                            toJSon.append("}]");
                        }

                    }

                    response.setContentType("aplication/json;charset=UTF-8");
                    try (PrintWriter out = response.getWriter()) {
                        out.println(toJSon);

                    }

                }

                break;

            default:
                break;

        }

    }

    private List<RegionDTO> llenarListaRegionesBD() {

        PreparedStatement ps;
        ResultSet res;
        List<RegionDTO> listRegiones = new ArrayList();

        try {

            ps = con.getCnn().prepareStatement("SELECT * FROM region");
            res = ps.executeQuery();
            while (res.next()) {
                listRegiones.add(new RegionDTO(res.getInt("idRegion"), res.getString("nombreRegion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Control1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }
        return listRegiones;

    }

    private List<ComunaDTO> llenarListaComunasBD(int codRegion) {

        PreparedStatement ps;
        ResultSet res;
        List<ComunaDTO> listComunas = new ArrayList();

        try {

            ps = con.getCnn().prepareStatement("SELECT * FROM comuna WHERE fkIdRegion =" + codRegion);
            res = ps.executeQuery();
            while (res.next()) {
                listComunas.add(new ComunaDTO(res.getInt("idComuna"), res.getString("nombreComuna"), res.getInt("fkIdRegion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Control1.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.cerrarConexion();
        }

        return listComunas;

    }

    //    private List<RegionDTO> llenarListaRegiones() {
//
//        RegionDTO reg1 = new RegionDTO(1, "Arica y Parinacota");
//        RegionDTO reg2 = new RegionDTO(2, "Antofagasta");
//        RegionDTO reg3 = new RegionDTO(3, "Atacama");
//
//        List<RegionDTO> listRegiones = new ArrayList();
//        listRegiones.add(reg1);
//        listRegiones.add(reg2);
//        listRegiones.add(reg3);
//
//        return listRegiones;
//
//    }
//    private List<ComunaDTO> llenarListaComunas(int codRegion) {
//
//        List<ComunaDTO> listComunas = new ArrayList();
//        ComunaDTO com1;
//        ComunaDTO com2;
//        ComunaDTO com3;
//
//        switch (codRegion) {
//
//            case 0:
//
//                break;
//            case 1:
//                com1 = new ComunaDTO(1, "Alto Hospicio", 1);
//                com2 = new ComunaDTO(2, "Iquique", 1);
//                com3 = new ComunaDTO(3, "Camiña", 1);
//                listComunas.add(com1);
//                listComunas.add(com2);
//                listComunas.add(com3);
//                break;
//
//            case 2:
//                com1 = new ComunaDTO(4, "Mejillones", 2);
//                com2 = new ComunaDTO(5, "Sierra Gorda", 2);
//                com3 = new ComunaDTO(6, "Taltal", 2);
//                listComunas.add(com1);
//                listComunas.add(com2);
//                listComunas.add(com3);
//                break;
//
//            case 3:
//                com1 = new ComunaDTO(7, "Chañaral", 3);
//                com2 = new ComunaDTO(8, "Diego De Almagro", 3);
//                com3 = new ComunaDTO(9, "Caldera", 3);
//                listComunas.add(com1);
//                listComunas.add(com2);
//                listComunas.add(com3);
//                break;
//
//            default:
//                break;
//        }
//
//        return listComunas;
//
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
