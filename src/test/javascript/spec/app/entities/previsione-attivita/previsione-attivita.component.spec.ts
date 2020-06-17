import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { PrevisioneAttivitaComponent } from 'app/entities/previsione-attivita/previsione-attivita.component';
import { PrevisioneAttivitaService } from 'app/entities/previsione-attivita/previsione-attivita.service';
import { PrevisioneAttivita } from 'app/shared/model/previsione-attivita.model';

describe('Component Tests', () => {
  describe('PrevisioneAttivita Management Component', () => {
    let comp: PrevisioneAttivitaComponent;
    let fixture: ComponentFixture<PrevisioneAttivitaComponent>;
    let service: PrevisioneAttivitaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [PrevisioneAttivitaComponent],
      })
        .overrideTemplate(PrevisioneAttivitaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PrevisioneAttivitaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PrevisioneAttivitaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new PrevisioneAttivita(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.previsioneAttivitas && comp.previsioneAttivitas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
