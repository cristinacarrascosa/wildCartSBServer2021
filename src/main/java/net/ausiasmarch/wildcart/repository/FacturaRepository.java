/*
 * Copyright (c) 2021
 *
 * by Rafael Angel Aznar Aparici (rafaaznar at gmail dot com) & 2021 DAW students
 *
 * WILDCART: Free Open Source Shopping Site
 *
 * Sources at:                https://github.com/rafaelaznar/wildCartSBServer2021
 * Database at:               https://github.com/rafaelaznar/wildCartSBServer2021
 * POSTMAN API at:            https://github.com/rafaelaznar/wildCartSBServer2021
 * Client at:                 https://github.com/rafaelaznar/wildCartAngularClient2021
 *
 * WILDCART is distributed under the MIT License (MIT)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package net.ausiasmarch.wildcart.repository;

import net.ausiasmarch.wildcart.entity.FacturaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FacturaRepository extends JpaRepository<FacturaEntity, Long> {

    @Query(value = "SELECT * FROM factura WHERE id_usuario = ?1", nativeQuery = true)
    Page<FacturaEntity> findByUsuarioId(Long id_usuario, Pageable pageable);

    @Query(value = "SELECT * FROM factura WHERE (iva LIKE  %?1% OR fecha LIKE %?2%)", nativeQuery = true)
    Page<FacturaEntity> findByIvaContainingOrFechaContaining(String iva, String fecha, Pageable oPageable);

    @Query(value = "SELECT * FROM factura WHERE id_usuario = ?1 AND (iva LIKE  %?2% OR fecha LIKE %?3%)", nativeQuery = true)
    Page<FacturaEntity> findByUsuarioIdAndIvaContainingOrFechaContaining(long id_usuario, String iva, String fecha, Pageable oPageable);
    
    @Query(value = "SELECT SUM(c.cantidad * c.precio) FROM factura f, compra c WHERE f.id_usuario = ?1 AND c.id_factura = f.id", nativeQuery = true)
    Double getTotalFacturasUsuario(long id_usuario);
    
    @Query(value = "SELECT SUM(c.cantidad * c.precio) FROM factura f, compra c WHERE f.id = ?1 AND c.id_factura = f.id", nativeQuery = true)
    Double getTotalFactura(long id_factura);
}
