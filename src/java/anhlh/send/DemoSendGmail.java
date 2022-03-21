/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package anhlh.send;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class DemoSendGmail {
    public static void main(String[] args) throws MessagingException {
        sendHTML("honganhle0a@gmail.com", 12, "123");
    }

    public static void sendHTML(String gmail, double total, String orderID) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        // Step1: setup Mail Server
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(gmail)); //Thay abc bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
        mailMessage.setSubject("Thank you for your order!");
        String emailBody = "<!DOCTYPE html>\n"
                + "<html>\n"
                + "\n"
                + "<head>\n"
                + "    <title></title>\n"
                + "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n"
                + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\" />\n"
                + "    <style type=\"text/css\">\n"
                + "        body,\n"
                + "        table,\n"
                + "        td,\n"
                + "        a {\n"
                + "            -webkit-text-size-adjust: 100%;\n"
                + "            -ms-text-size-adjust: 100%;\n"
                + "        }\n"
                + "\n"
                + "        table,\n"
                + "        td {\n"
                + "            mso-table-lspace: 0pt;\n"
                + "            mso-table-rspace: 0pt;\n"
                + "        }\n"
                + "\n"
                + "        img {\n"
                + "            -ms-interpolation-mode: bicubic;\n"
                + "        }\n"
                + "\n"
                + "        img {\n"
                + "            border: 0;\n"
                + "            height: auto;\n"
                + "            line-height: 100%;\n"
                + "            outline: none;\n"
                + "            text-decoration: none;\n"
                + "        }\n"
                + "\n"
                + "        table {\n"
                + "            border-collapse: collapse !important;\n"
                + "        }\n"
                + "\n"
                + "        body {\n"
                + "            height: 100% !important;\n"
                + "            margin: 0 !important;\n"
                + "            padding: 0 !important;\n"
                + "            width: 100% !important;\n"
                + "        }\n"
                + "\n"
                + "        a[x-apple-data-detectors] {\n"
                + "            color: inherit !important;\n"
                + "            text-decoration: none !important;\n"
                + "            font-size: inherit !important;\n"
                + "            font-family: inherit !important;\n"
                + "            font-weight: inherit !important;\n"
                + "            line-height: inherit !important;\n"
                + "        }\n"
                + "\n"
                + "        @media screen and (max-width: 480px) {\n"
                + "            .mobile-hide {\n"
                + "                display: none !important;\n"
                + "            }\n"
                + "\n"
                + "            .mobile-center {\n"
                + "                text-align: center !important;\n"
                + "            }\n"
                + "        }\n"
                + "\n"
                + "        div[style*=\"margin: 16px 0;\"] {\n"
                + "            margin: 0 !important;\n"
                + "        }\n"
                + "    </style>\n"
                + "\n"
                + "<body style=\"margin: 0 !important; padding: 0 !important; background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n"
                + "    <div style=\"display: none; font-size: 1px; color: #fefefe; line-height: 1px; font-family: Open Sans, Helvetica, Arial, sans-serif; max-height: 0px; max-width: 0px; opacity: 0; overflow: hidden;\">\n"
                + "        For what reason would it be advisable for me to think about business content? That might be little bit risky to have crew member like them.\n"
                + "    </div>\n"
                + "    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n"
                + "        <tr>\n"
                + "            <td align=\"center\" style=\"background-color: #eeeeee;\" bgcolor=\"#eeeeee\">\n"
                + "                <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n"
                + "                    <tr>\n"
                + "                        <td align=\"center\" valign=\"top\" style=\"font-size:0; padding: 35px;\" bgcolor=\"#F44336\">\n"
                + "                            <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\">\n"
                + "                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n"
                + "                                    <tr>\n"
                + "                                        <td align=\"left\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 36px; font-weight: 800; line-height: 48px;\" class=\"mobile-center\">\n"
                + "                                            <h1 style=\"font-size: 36px; font-weight: 800; margin: 0; color: #ffffff;\">VegetableShop</h1>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </table>\n"
                + "                            </div>\n"
                + "                            <div style=\"display:inline-block; max-width:50%; min-width:100px; vertical-align:top; width:100%;\" class=\"mobile-hide\">\n"
                + "                                <table align=\"left\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:300px;\">\n"
                + "                                    <tr>\n"
                + "                                        <td align=\"right\" valign=\"top\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 48px; font-weight: 400; line-height: 48px;\">\n"
                + "                                            <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" align=\"right\">\n"
                + "                                                <tr>\n"
                + "                                                    <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400;\">\n"
                + "                                                        <p style=\"font-size: 18px; font-weight: 400; margin: 0; color: #ffffff;\"><a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\">Shop &nbsp;</a></p>\n"
                + "                                                    </td>\n"
                + "                                                    <td style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 18px; font-weight: 400; line-height: 24px;\"> <a href=\"#\" target=\"_blank\" style=\"color: #ffffff; text-decoration: none;\"><img src=\"https://img.icons8.com/color/48/000000/small-business.png\" width=\"27\" height=\"23\" style=\"display: block; border: 0px;\" /></a> </td>\n"
                + "                                                </tr>\n"
                + "                                            </table>\n"
                + "                                        </td>\n"
                + "                                    </tr>\n"
                + "                                </table>\n"
                + "                            </div>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                    <tr>\n"
                + "                        <td align=\"center\" style=\"padding: 35px 35px 20px 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n"
                + "                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n"
                + "                                <tr>\n"
                + "                                    <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 25px;\"> <img src=\"https://img.icons8.com/carbon-copy/100/000000/checked-checkbox.png\" width=\"125\" height=\"120\" style=\"display: block; border: 0px;\" /><br>\n"
                + "                                        <h2 style=\"font-size: 30px; font-weight: 800; line-height: 36px; color: #333333; margin: 0;\"> Thank You For Your Order! </h2>\n"
                + "                                    </td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding-top: 10px;\">\n"
                + "                                        <p style=\"font-size: 16px; font-weight: 400; line-height: 24px; color: #777777;\"> Lorem ipsum dolor sit amet, consectetur adipisicing elit. Praesentium iste ipsa numquam odio dolores, nam. </p>\n"
                + "                                    </td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td align=\"left\" style=\"padding-top: 20px;\">\n"
                + "                                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                + "                                            <tr>\n"
                + "                                                <td width=\"75%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\"> Order Confirmation # </td>\n"
                + "                                                <td width=\"25%\" align=\"left\" bgcolor=\"#eeeeee\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px;\"> "+orderID+" </td>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td width=\"75%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> Purchased Item (1) </td>\n"
                + "                                                <td width=\"25%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 15px 10px 5px 10px;\"> $"+total+" </td>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td width=\"75%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\"> Shipping + Handling </td>\n"
                + "                                                <td width=\"25%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\"> $0.00 </td>\n"
                + "                                            </tr>\n"
                + "                                            <tr>\n"
                + "                                                <td width=\"75%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\"> Sales Tax </td>\n"
                + "                                                <td width=\"25%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 400; line-height: 24px; padding: 5px 10px;\"> $0.00 </td>\n"
                + "                                            </tr>\n"
                + "                                        </table>\n"
                + "                                    </td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td align=\"left\" style=\"padding-top: 20px;\">\n"
                + "                                        <table cellspacing=\"0\" cellpadding=\"0\" border=\"0\" width=\"100%\">\n"
                + "                                            <tr>\n"
                + "                                                <td width=\"75%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"> TOTAL </td>\n"
                + "                                                <td width=\"25%\" align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 16px; font-weight: 800; line-height: 24px; padding: 10px; border-top: 3px solid #eeeeee; border-bottom: 3px solid #eeeeee;\"> $"+total+" </td>\n"
                + "                                            </tr>\n"
                + "                                        </table>\n"
                + "                                    </td>\n"
                + "                                </tr>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "\n"
                + "                    <tr>\n"
                + "                        <td align=\"center\" style=\"padding: 35px; background-color: #ffffff;\" bgcolor=\"#ffffff\">\n"
                + "                            <table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"max-width:600px;\">\n"
                + "                                <tr>\n"
                + "                                    <td align=\"center\">  </td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td align=\"center\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 24px; padding: 5px 0 10px 0;\">\n"
                + "                                        <p style=\"font-size: 14px; font-weight: 800; line-height: 18px; color: #333333;\"> 675 Parko Avenue<br> LA, CA 02232 </p>\n"
                + "                                    </td>\n"
                + "                                </tr>\n"
                + "                                <tr>\n"
                + "                                    <td align=\"left\" style=\"font-family: Open Sans, Helvetica, Arial, sans-serif; font-size: 14px; font-weight: 400; line-height: 24px;\">\n"
                + "                                        <p style=\"font-size: 14px; font-weight: 400; line-height: 20px; color: #777777;\"> If you didn't create an account using this email address, please ignore this email or <a href=\"#\" target=\"_blank\" style=\"color: #777777;\">unsusbscribe</a>. </p>\n"
                + "                                    </td>\n"
                + "                                </tr>\n"
                + "                            </table>\n"
                + "                        </td>\n"
                + "                    </tr>\n"
                + "                </table>\n"
                + "            </td>\n"
                + "        </tr>\n"
                + "    </table>\n"
                + "</body>\n"
                + "\n"
                + "</html>";
        mailMessage.setContent(emailBody, "text/html");

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
        transport.connect("smtp.gmail.com", "shopvegetabledemo@gmail.com", "Anh123456@");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

}
