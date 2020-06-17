import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { EoloprjTestModule } from '../../../test.module';
import { RappresentanzaPraticaComponent } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica.component';
import { RappresentanzaPraticaService } from 'app/entities/rappresentanza-pratica/rappresentanza-pratica.service';
import { RappresentanzaPratica } from 'app/shared/model/rappresentanza-pratica.model';

describe('Component Tests', () => {
  describe('RappresentanzaPratica Management Component', () => {
    let comp: RappresentanzaPraticaComponent;
    let fixture: ComponentFixture<RappresentanzaPraticaComponent>;
    let service: RappresentanzaPraticaService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [RappresentanzaPraticaComponent],
      })
        .overrideTemplate(RappresentanzaPraticaComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(RappresentanzaPraticaComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(RappresentanzaPraticaService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new RappresentanzaPratica(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.rappresentanzaPraticas && comp.rappresentanzaPraticas[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
