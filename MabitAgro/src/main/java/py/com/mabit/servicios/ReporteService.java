package py.com.mabit.servicios;

import java.io.ByteArrayOutputStream;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@Service
public class ReporteService {

	@Autowired
	private DataSource dataSource;

	public byte[] generarReporte(Map<String, Object> parametros, String nombreReporte, String formato)
			throws Exception {
		try {
			String rutaReporte = ResourceUtils.getFile("classpath:reportes/" + nombreReporte + ".jasper")
					.getAbsolutePath();
			JasperPrint jasperPrint = JasperFillManager.fillReport(rutaReporte, parametros, dataSource.getConnection());

			switch (formato.toLowerCase()) {
			case "pdf":
				return JasperExportManager.exportReportToPdf(jasperPrint);
			case "xlsx":
				JRXlsxExporter exporter = new JRXlsxExporter();
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
				exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(out));
				exporter.exportReport();
				return out.toByteArray();
			default:
				throw new IllegalArgumentException("Formato no soportado: " + formato);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error al generar el reporte: " + nombreReporte + e.getMessage());
		}
	}
}