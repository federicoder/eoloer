import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { TemplatePraticaDetailComponent } from 'app/entities/template-pratica/template-pratica-detail.component';
import { TemplatePratica } from 'app/shared/model/template-pratica.model';

describe('Component Tests', () => {
  describe('TemplatePratica Management Detail Component', () => {
    let comp: TemplatePraticaDetailComponent;
    let fixture: ComponentFixture<TemplatePraticaDetailComponent>;
    const route = ({ data: of({ templatePratica: new TemplatePratica(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [TemplatePraticaDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(TemplatePraticaDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(TemplatePraticaDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load templatePratica on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.templatePratica).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
