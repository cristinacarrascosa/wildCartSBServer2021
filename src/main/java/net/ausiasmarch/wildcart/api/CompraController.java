package net.ausiasmarch.wildcart.api;

import net.ausiasmarch.wildcart.entity.CompraEntity;
import net.ausiasmarch.wildcart.service.CompraService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compra")
public class CompraController {

    @Autowired
    CompraService oCompraService;

    @GetMapping("/{id}")
    public ResponseEntity<CompraEntity> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<CompraEntity>(oCompraService.get(id), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> count() {
        return new ResponseEntity<Long>(oCompraService.count(), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getPage(
            @ParameterObject @PageableDefault(page = 0, size = 10, direction = Sort.Direction.DESC) Pageable oPageable,
            @RequestParam(name = "filter", required = false) String strFilter,
            @RequestParam(name = "factura", required = false) Long lFactura,
            @RequestParam(name = "producto", required = false) Long lProducto) {
        return new ResponseEntity<Page<CompraEntity>>(oCompraService.getPage(oPageable, strFilter, lFactura, lProducto), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Long> create(@RequestBody CompraEntity oCompraEntity) {
        return new ResponseEntity<Long>(oCompraService.create(oCompraEntity), HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<Long> update(@RequestBody CompraEntity oCompraEntity) {
        return new ResponseEntity<Long>(oCompraService.update(oCompraEntity), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<Long>(oCompraService.delete(id), HttpStatus.OK);
    }

    @PostMapping("/generate")
    public ResponseEntity<CompraEntity> generate() {
        return new ResponseEntity<CompraEntity>(oCompraService.generate(), HttpStatus.OK);
    }        

    @PostMapping("/generate/{amount}")
    public ResponseEntity<Long> generateSome(@PathVariable(value = "amount") Integer amount) {
        return new ResponseEntity<Long>(oCompraService.generateSome(amount), HttpStatus.OK);
    }
}
