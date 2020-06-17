import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { CondivisionePraticaComponent } from 'app/entities/condivisione-pratica/condivisione-pratica.component';
import { CondivisionePraticaService } from 'app/entities/condivisione-pratica/condivisione-pratica.service';
import { CondivisionePratica } from 'app/shared/model/condivisione-pratica.model';

describe('Component Tests', () => {
  describe('CondivisionePratica Management Component', () => {
    let comp: CondivisionePraticaComponent;
    let fixture: ComponentFixture<CondivisionePraticaComponent>;
    let service: CondivisionePraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [CondivisionePraticaComponent],
      })
        .overrideTemplate(CondivisionePraticaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(CondivisionePraticaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(CondivisionePraticaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new CondivisionePratica(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.condivisionePraticas && comp.condivisionePraticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
