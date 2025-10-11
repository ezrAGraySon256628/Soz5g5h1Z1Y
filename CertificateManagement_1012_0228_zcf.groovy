// 代码生成时间: 2025-10-12 02:28:32
class CertificateManagement {

    /***
     * 存储证书的Map，键是证书ID，值是证书对象
     *
     * @param certificates
     *           证书集合
     */
    private Map<String, Certificate> certificates = new HashMap<>();

    /***
     * 添加证书
     *
     * @param certificate
     *           要添加的证书对象
     * @return 添加成功返回证书ID，失败返回null
     */
    String addCertificate(Certificate certificate) {
        if (certificate == null || certificate.id == null) {
            // 错误处理：证书或证书ID为空
            println 'Error: Certificate or certificate ID cannot be null.'
            return null
        }

        if (certificates.containsKey(certificate.id)) {
            // 错误处理：证书ID已存在
            println 'Error: Certificate with ID ' + certificate.id + ' already exists.'
            return null
        }

        certificates.put(certificate.id, certificate)
        return certificate.id
    }

    /***
     * 删除证书
     *
     * @param certificateId
     *           要删除的证书ID
     * @return 删除成功返回true，失败返回false
     */
    boolean removeCertificate(String certificateId) {
        if (certificates.remove(certificateId) == null) {
            // 错误处理：证书ID不存在
            println 'Error: Certificate with ID ' + certificateId + ' not found.'
            return false
        }

        return true
    }

    /***
     * 更新证书
     *
     * @param certificate
     *           要更新的证书对象
     * @return 更新成功返回true，失败返回false
     */
    boolean updateCertificate(Certificate certificate) {
        if (certificate == null || certificate.id == null) {
            // 错误处理：证书或证书ID为空
            println 'Error: Certificate or certificate ID cannot be null.'
            return false
        }

        if (!certificates.containsKey(certificate.id)) {
            // 错误处理：证书ID不存在
            println 'Error: Certificate with ID ' + certificate.id + ' not found.'
            return false
        }

        certificates.put(certificate.id, certificate)
        return true
    }

    /***
     * 获取证书
     *
     * @param certificateId
     *           要获取的证书ID
     * @return 证书对象，如果不存在返回null
     */
    Certificate getCertificate(String certificateId) {
        return certificates.get(certificateId)
    }

    /***
     * 获取所有证书
     *
     * @return 所有证书的集合
     */
    Collection<Certificate> getAllCertificates() {
        return certificates.values()
    }
}

/***
 * 证书对象
 */
class Certificate {
    String id
    String name
    String issuer
    String expirationDate
    String serialNumber

    // 构造方法
    Certificate(String id, String name, String issuer, String expirationDate, String serialNumber) {
        this.id = id
        this.name = name
        this.issuer = issuer
        this.expirationDate = expirationDate
        this.serialNumber = serialNumber
    }

    // Getter和Setter方法
    String getId() { id }
    void setId(String id) { this.id = id }
    String getName() { name }
    void setName(String name) { this.name = name }
    String getIssuer() { issuer }
    void setIssuer(String issuer) { this.issuer = issuer }
    String getExpirationDate() { expirationDate }
    void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate }
    String getSerialNumber() { serialNumber }
    void setSerialNumber(String serialNumber) { this.serialNumber = serialNumber }
}
