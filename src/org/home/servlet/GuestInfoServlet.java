package org.home.servlet;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.home.service.GuestService;

import java.io.IOException;

@WebServlet("/guest-info")
public class GuestInfoServlet extends HttpServlet {
  private final GuestService guestService = GuestService.getInstance();

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
    var guestId = Long.valueOf(req.getParameter("guestId"));
    resp.setContentType("text/html; charset=UTF-8");
    try (var printWriter = resp.getWriter()) {
      guestService.findByGuestId(guestId).ifPresent(guestDto -> printWriter.write("""
          <h1>%s, %s</h1>
          <div>
            check-in:&ensp;%s
            <br>
            check-out:&nbsp;%s
            <br>
            room: %s
          </div>
          """.formatted(guestDto.fio(), guestDto.birthday(), guestDto.checkIn(), guestDto.checkOut(), guestDto.roomNumber())));
    }
  }
}
